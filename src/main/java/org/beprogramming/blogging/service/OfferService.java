package org.beprogramming.blogging.service;

import java.util.List;
import java.util.Random;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.beprogramming.blogging.model.Offer;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class OfferService extends CrudService<Offer> {

	private static Random r = new Random();

	public Offer getAdUnit(final Integer width, final Integer height, final String placement) {

		final TypedQuery<Offer> offerQuery = getEntityManager().createQuery("SELECT c FROM Offer c WHERE c.width = :width and " + "c.height = :height and " + "c.placement = :placement and c.enabled = true", Offer.class);

		offerQuery.setParameter("width", width);
		offerQuery.setParameter("height", height);
		offerQuery.setParameter("placement", placement);

		return getOffer(offerQuery.getResultList());

	}

	public Offer getAdUnit(final String placement) {

		final TypedQuery<Offer> offerQuery = getEntityManager().createQuery("SELECT c FROM Offer c WHERE c.placement = :placement and c.enabled = true", Offer.class);

		offerQuery.setParameter("placement", placement);

		return getOffer(offerQuery.getResultList());
	}

	@Override
	public Object getID(final Offer item) {

		return item.getOfferID();
	}

	public Offer getOffer(final List<Offer> offerList) {

		Integer numberOfUnits = 0;

		for (final Offer offer : offerList)
			numberOfUnits += offer.getRotation();
		if (numberOfUnits <= 0)
			return null;
		final Integer randomOffer = r.nextInt(numberOfUnits);
		final Integer currentUnit = 0;

		for (final Offer offer : offerList)
			if (randomOffer <= currentUnit + offer.getRotation())
				return offer;

		return null;
	}

	@Override
	protected Class<Offer> getTypeClass() {

		return Offer.class;
	}

}
