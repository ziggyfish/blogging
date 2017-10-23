package org.beprogramming.blogging.service;

import java.util.LinkedHashMap;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Post;

@Singleton
public class CounterService {

	private class CachingMap extends LinkedHashMap<Integer, Integer> {

		/**
		 *
		 */
		private static final long	serialVersionUID	= 1L;

		private static final int	MAX_ENTRIES			= 5;

		@Override
		protected boolean removeEldestEntry(final java.util.Map.Entry<Integer, Integer> eldest) {

			if (size() > MAX_ENTRIES) {
				final Post p = service.find(eldest.getKey());
				p.setNumberOfVisits(eldest.getValue());
				service.update(p);
			}

			return size() > MAX_ENTRIES;
		}

	}

	@Inject
	private PostService	service;

	CachingMap			map	= new CachingMap();

	public Integer getCurrentVisits(final Integer postID) {

		if (map.get(postID) != null)
			return map.get(postID);
		return service.find(postID).getNumberOfVisits();

	}

	public Integer recordVisit(final Integer postID) {

		synchronized (postID) {
			if (map.get(postID) == null) {
				final Post p = service.find(postID);
				if (p.getNumberOfVisits() == null)
					map.put(postID, 0);
				else
					map.put(postID, p.getNumberOfVisits());

			}
			map.put(postID, map.get(postID) + 1);
		}
		return map.get(postID);
	}

	@Schedule(hour = "*", minute = "*")
	public void timeout(final Timer timer) {

		map.forEach((postID, count) -> {
			final Post p = service.find(postID);
			p.setNumberOfVisits(count);
			service.update(p);
		});
		map.clear();
	}
}
