package org.beprogramming.blogging.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/track")
public class Track {

	private static String	URL		= "http://www.plus500.com/?id=98806&pl=2";

	private Integer			count	= 0;

	@Path("/count")
	@GET
	public String getCount() {

		return count.toString();
	}

	@Path("/go")
	@GET
	public Response trackClick() throws URISyntaxException {

		count++;
		return Response.temporaryRedirect(new URI(URL)).build();
	}
}
