package org.beprogramming.blogging.rest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.beprogramming.blogging.controller.HelperController;
import org.beprogramming.blogging.model.Cartoon;
import org.beprogramming.blogging.model.Category;
import org.beprogramming.blogging.model.Post;
import org.beprogramming.blogging.model.sitemap.URL;
import org.beprogramming.blogging.model.sitemap.Urlset;
import org.beprogramming.blogging.model.sitemap.image.Image;
import org.beprogramming.blogging.model.sitemap.news.News;
import org.beprogramming.blogging.service.CartoonService;
import org.beprogramming.blogging.service.CategoryService;
import org.beprogramming.blogging.service.PostService;
import org.beprogramming.blogging.util.Utility;

@RequestScoped
@Path("/sitemap")
public class Sitemap {

	@Inject
	private PostService			postService;

	@Inject
	private CartoonService		cartoonService;

	@Inject
	private CategoryService		categorySerivce;

	@Inject
	private HelperController	helper;

	private void addURL(final Urlset data, final String url) {

		final URL u = new URL();
		u.loc = url;
		data.url.add(u);
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML })
	public Urlset get() {

		final Urlset data = new Urlset();
		addURL(data, "http://zanettisview.com/");
		addURL(data, "http://zanettisview.com/about.jsf");
		final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		for (final Post p : postService.findAll()) {
			final URL u = new URL();
			u.loc = "http://zanettisview.com" + Utility.getPostURL(p);
			u.news = new News();
			u.news.title = p.getTitle();
			u.news.keywords = p.getKeywords();
			u.news.genres = p.getGenres();
			u.news.publication_date = fmt.format(p.getCreated());
			data.url.add(u);
		}
		addURL(data, "http://zanettisview.com/cartoons.jsf");
		for (final Cartoon p : cartoonService.findAll()) {
			final URL u = new URL();
			u.loc = "http://zanettisview.com" + Utility.getCartoonURL(p);
			u.image = new Image();

			u.image.loc = "http://zanettisview.com/rest/images/" + p.getMediaFile().getFileID();
			data.url.add(u);

		}
		for (final Category p : categorySerivce.findAll())
			addURL(data, "http://zanettisview.com" + Utility.getCategoryURL(p));

		return data;
	}

	@GET
	@Path("/rss")
	@Produces({ MediaType.APPLICATION_XML })
	public String getRss() {

		final StringBuilder str = new StringBuilder();
		final String link = "http://zanettisview.com/rest/sitemap/rss";
		str.append("<?xml version=\"1.0\"?>\n");
		str.append("<rss version=\"2.0\" xmlns:media=\"http://search.yahoo.com/mrss/\" xmlns:georss=\"http://www.georss.org/georss\" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n");
		str.append("<channel>\n");
		str.append("<title>Zanetti's View</title>\n");
		str.append("<link>" + link + "</link>\n");
		str.append("<description>Latest Results from Zanetti's View</description>\n");
		str.append("<copyright>2015 Zanetti's View</copyright>\n");
		str.append("<language>en-au</language>\n");
		str.append("<ttl>15</ttl>\n");
		str.append("<pubDate>" + Utility.convertDateToRSS(new Date()) + "</pubDate>\n");
		str.append("<atom:link href=\"http://www.abc.net.au/local/rss/recipes.xml\" rel=\"self\" type=\"application/rss+xml\" />");
		for (final Post p : postService.findAll()) {
			// TODO:: loop through articles
			final String loc = "http://zanettisview.com" + Utility.getPostURL(p);
			final String title = p.getTitle();
			final String description = helper.htmlToText(p.getAbs().replaceAll("[\r\n]", "")).replaceAll("\\&.*\\;", "");
			str.append("<item>\n");
			str.append("<title>" + title + "</title>\n");
			str.append("<link>" + loc + "</link>\n");
			str.append("<description>" + description + "</description>\n");
			str.append("<pubDate>" + Utility.convertDateToRSS(p.getCreated()) + "</pubDate>");
			str.append("</item>");
		}

		// TODO: info
		str.append("</channel>");
		str.append("</rss>");

		return str.toString();
	}
}
