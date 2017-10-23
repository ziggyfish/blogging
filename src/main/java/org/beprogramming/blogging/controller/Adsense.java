package org.beprogramming.blogging.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class Adsense {

	private String clientId = "ca-pub-4849403104685453";

	public String getClientId() {

		return clientId;
	}

	public void setClientId(final String clientId) {

		this.clientId = clientId;
	}
}
