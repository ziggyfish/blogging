package org.beprogramming.blogging.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;

import org.beprogramming.blogging.model.Cartoon;
import org.beprogramming.blogging.model.Category;
import org.beprogramming.blogging.model.Post;
import org.beprogramming.blogging.model.Question;

public class Utility {

	private static final Pattern	NONLATIN		= Pattern.compile("[^\\w-]");

	private static final Pattern	WHITESPACE		= Pattern.compile("[\\s]+");

	private static final Pattern	DUPLICATEDASH	= Pattern.compile("[-]+");

	public static String convertDateToRSS(final Date d) {

		final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy");
		return sdf.format(d);
	}

	public static String getCartoonURL(final Cartoon c) {

		if (c.getStoryID() < 367)
			return getContextPath() + "/cartoon.jsf?cartoonid=" + c.getStoryID();
		if (c.getCaption() != null)
			return getContextPath() + "/cartoons/" + Utility.toSlug(c.getCaption()) + "/" + c.getStoryID();
		final SimpleDateFormat fmt = new SimpleDateFormat("yyMMdd");
		return getContextPath() + "/cartoons/" + fmt.format(c.getCreated()) + "/" + c.getStoryID();
	}

	public static String getCategoryURL(final Category cat) {

		return getContextPath() + "/category/" + Utility.toSlug(cat.getName()) + "/" + cat.getCategoryID();
	}

	public static String getContextPath() {

		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}

	public static String getPostURL(final Post p) {

		if (p.getPostID() < 367)
			return getContextPath() + "/article.jsf?postid=" + p.getPostID();
		return getContextPath() + "/story/" + Utility.toSlug(p.getTitle()) + "/" + p.getPostID();
	}

	public static String getQuestionURL(final Question q) {

		return getContextPath() + "/poll/" + Utility.toSlug(q.getQuestion()) + "/" + q.getQuestionID();
	}

	public static String hex(final byte[] array) {

		final StringBuffer sb = new StringBuffer();
		for (final byte element : array)
			sb.append(Integer.toHexString(element & 0xFF | 0x100).substring(1, 3));
		return sb.toString();
	}

	public static String md5Hex(final String message) {

		try {
			final MessageDigest md = MessageDigest.getInstance("MD5");
			return hex(md.digest(message.getBytes("CP1252")));
		} catch (final NoSuchAlgorithmException e) {
		} catch (final UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String toSlug(final String input) {

		final String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		final String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		final String slug = NONLATIN.matcher(normalized).replaceAll("");
		final String slugDash = DUPLICATEDASH.matcher(slug).replaceAll("-");
		return slugDash.toLowerCase(Locale.ENGLISH);
	}

}
