package org.beprogramming.blogging.rest;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.beprogramming.blogging.model.File;
import org.beprogramming.blogging.service.FileService;
import org.beprogramming.blogging.util.FileManager;

@RequestScoped
@Path("/images")
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class Image {

	private static double getAspectRatio(final int width, final int height) {

		return (double) height / (double) width;
	}

	public static BufferedImage getScaledInstance(final BufferedImage img, final int targetWidth, final int targetHeight, final Object hint, final boolean higherQuality) {

		final int type = img.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = img;
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (higherQuality) {
				if (w > targetWidth)
					w /= 2;
				if (w < targetWidth)
					w = targetWidth;
			}

			if (higherQuality) {
				if (h > targetHeight)
					h /= 2;
				if (h < targetHeight)
					h = targetHeight;
			}

			final BufferedImage tmp = new BufferedImage(w, h, type);
			final Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}

	private static BufferedImage resizeImage(final InputStream in, final Integer width, final Integer height) throws IOException {

		final BufferedImage originalImage = ImageIO.read(in);
		getAspectRatio(originalImage.getWidth(), originalImage.getHeight());
		// calc the desired height based on the origRatio (using the width) then crop the new image to the new image.
		int newWidth;
		int newHeight;
		double ratio;
		/*
		 * if (height > width) ratio = (double) height / (double) originalImage.getHeight(); else ratio = (double) width / (double) originalImage.getWidth();
		 */
		ratio = (double) width / (double) originalImage.getWidth();
		newWidth = (int) (originalImage.getWidth() * ratio) + 1;
		newHeight = (int) (originalImage.getHeight() * ratio) + 1;

		if (newHeight < height) {
			ratio = (double) height / (double) originalImage.getHeight();
			newWidth = (int) (originalImage.getWidth() * ratio);
			newHeight = (int) (originalImage.getHeight() * ratio) + 1;
		}
		int offsetX = 0;
		int offsetY = 0;
		if (newWidth != width)
			offsetX = (newWidth - width) / 2;
		if (newHeight != height)
			offsetY = (newHeight - height) / 2;

		return transform2(originalImage, ratio, newWidth, newHeight).getSubimage(offsetX, offsetY, width, height);

	}

	private static BufferedImage resizeImageHeight(final InputStream in, final Integer height) throws IOException {

		final BufferedImage originalImage = ImageIO.read(in);

		int newWidth;
		int newHeight;
		double ratio;
		ratio = (double) height / (double) originalImage.getHeight();
		newWidth = (int) (originalImage.getWidth() * ratio);
		newHeight = (int) (originalImage.getHeight() * ratio);
		return transform2(originalImage, ratio, newWidth, newHeight);

	}

	private static BufferedImage resizeImageWidth(final InputStream in, final Integer width) throws IOException {

		final BufferedImage originalImage = ImageIO.read(in);

		int newWidth;
		int newHeight;
		double ratio;
		ratio = (double) width / (double) originalImage.getWidth();
		newWidth = (int) (originalImage.getWidth() * ratio);
		newHeight = (int) (originalImage.getHeight() * ratio);

		return transform2(originalImage, ratio, newWidth, newHeight);
	}

	protected static BufferedImage transform(final BufferedImage originalImage, final double ratio, final int newWidth, final int newHeight) {

		final int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		final BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
		final Graphics2D g = resizedImage.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
		g.dispose();

		return resizedImage;
	}

	protected static BufferedImage transform2(final BufferedImage originalImage, final double ratio, final int newWidth, final int newHeight) {

		// return getScaledInstance(originalImage, newWidth, newHeight, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		return getScaledInstance(originalImage, newWidth, newHeight, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
	}

	@Inject
	private FileService service;

	private ByteArrayOutputStream doGet(final File f, final ImageRequestData data) throws IOException {

		// Resize the image to the desired width and height.
		BufferedImage img;
		if (data.width != null && data.height != null)
			img = resizeImage(FileManager.getFileData(data.id), data.width, data.height);
		else if (data.width == null && data.height == null) {
			final ByteArrayOutputStream imgData = new ByteArrayOutputStream();
			imgData.write(f.getImage());
			return imgData;
		} else if (data.width == null)
			img = resizeImageHeight(FileManager.getFileData(data.id), data.height);
		else
			img = resizeImageWidth(FileManager.getFileData(data.id), data.width);

		final ByteArrayOutputStream out = new ByteArrayOutputStream();

		ImageIO.write(img, "png", out);
		return out;

	}

	private Response get(final Request request, final ImageRequestData data) throws IOException {

		final CacheControl cc = new CacheControl();
		cc.setPrivate(false);
		cc.setMaxAge(86400);
		final Date date = new Date(109, 1, 1, 1, 1, 1);
		final EntityTag etag = new EntityTag(Integer.toString(data.hashCode()));
		ResponseBuilder builder = request.evaluatePreconditions(date, etag);
		if (builder == null) {
			final java.nio.file.Path result = FileManager.getCache(data);
			if (result != null)
				builder = Response.ok(new ImageFileOutputStream(result));
			else {
				final File f = service.find(data.id);
				final ByteArrayOutputStream resultData = doGet(f, data);
				FileManager.putCache(data, resultData);
				builder = Response.ok(new ImageOutputStream(new ByteArrayInputStream(resultData.toByteArray())));

			}
			builder.tag(etag);
			builder.type("image/png");
		}

		builder.cacheControl(cc);

		builder.lastModified(date);
		return builder.build();

	}

	@GET
	@Path("/{id}")
	public Response getImage(@PathParam("id") final Integer id, @Context final Request request, @Context final HttpHeaders headers) throws IOException {

		return get(request, new ImageRequestData(id, null, null));
	}

	@GET
	@Path("/{id}/h{height}.png")
	public Response getImageByHeight(@PathParam("id") final Integer id, @PathParam("height") final Integer height, @Context final Request request) throws IOException {

		return get(request, new ImageRequestData(id, null, height));
	}

	@GET
	@Path("/{id}/w{width}.png")
	public Response getImageByWidth(@PathParam("id") final Integer id, @PathParam("width") final Integer width, @Context final Request request) throws IOException {

		return get(request, new ImageRequestData(id, width, null));
	}

	@GET
	@Path("/{id}/w{width}h{height}.png")
	public Response getImageByWidthHeight(@PathParam("id") final Integer id, @PathParam("width") final Integer width, @PathParam("height") final Integer height, @Context final Request request) throws IOException {

		return get(request, new ImageRequestData(id, width, height));
	}

	@GET
	@Path("/move")
	public String move() throws IOException {

		final List<File> all = service.findAll();
		for (final File f : all)
			FileManager.saveFile(f.getFileID(), f.getImage());
		return "OK";
	}

}
