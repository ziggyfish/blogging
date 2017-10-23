package org.beprogramming.blogging.controller;

import java.net.URLEncoder;
import java.util.Enumeration;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.beprogramming.blogging.util.Utility;

@RequestScoped
@ManagedBean
public class HelperController {

	public String getGravatarURL(final String str) {

		return Utility.md5Hex(str);
	}

	public String getURL() {

		Enumeration<String> lParameters;
		String sParameter;
		final StringBuilder sbURL = new StringBuilder();
		final Object oRequest = FacesContext.getCurrentInstance().getExternalContext().getRequest();

		try {
			if (oRequest instanceof HttpServletRequest) {
				sbURL.append(((HttpServletRequest) oRequest).getRequestURL().toString());

				lParameters = ((HttpServletRequest) oRequest).getParameterNames();

				if (lParameters.hasMoreElements())
					if (!sbURL.toString().contains("?"))
						sbURL.append("?");
					else
						sbURL.append("&");

				while (lParameters.hasMoreElements()) {
					sParameter = lParameters.nextElement();

					sbURL.append(sParameter);
					sbURL.append("=");
					sbURL.append(URLEncoder.encode(((HttpServletRequest) oRequest).getParameter(sParameter), "UTF-8"));

					if (lParameters.hasMoreElements())
						sbURL.append("&");
				}
			}
		} catch (final Exception e) {
			// Do nothing
		}

		return sbURL.toString();
	}

	public String htmlToText(final String html) {

		return html.replaceAll("\n", " ").replaceAll("\\<.*?>", "");
	}
}
