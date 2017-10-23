package org.beprogramming.blogging.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public class ImageOutputStream implements StreamingOutput {

	private final InputStream stream;

	public ImageOutputStream(final InputStream stream) {

		this.stream = stream;
	}

	public void copyStream(final InputStream input, final OutputStream output) throws IOException {

		final byte[] buffer = new byte[1024]; // Adjust if you want
		int bytesRead;
		while ((bytesRead = input.read(buffer)) != -1)
			output.write(buffer, 0, bytesRead);
		output.close();
	}

	@Override
	public void write(final OutputStream arg0) throws IOException, WebApplicationException {

		copyStream(stream, arg0);

	}
}
