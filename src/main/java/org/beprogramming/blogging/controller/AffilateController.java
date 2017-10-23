package org.beprogramming.blogging.controller;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class AffilateController {

	private static String[]	PLUS_500	= { "http://cdn.plus500.com/Media/Banners/300x250/12525.gif", "https://cdn.plus500.com/Media/Banners/300x250/14012.gif", "https://cdn.plus500.com/Media/Banners/300x250/13873.gif", "https://cdn.plus500.com/Media/Banners/300x250/13016.gif" };

	private final Random	r			= new Random();

	public String getAdUnit(final Integer width, final Integer height) {

		return "";
	}

	public String getForex() {

		return PLUS_500[r.nextInt(PLUS_500.length)];
	}
}
