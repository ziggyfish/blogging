package org.beprogramming.blogging.model.sitemap;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Urlset {
	public ArrayList<URL>	url	= new ArrayList<URL>();
}
