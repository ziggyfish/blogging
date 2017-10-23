package org.beprogramming.blogging.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public class ImageFileOutputStream implements StreamingOutput {

	private final Path p;

	public ImageFileOutputStream(final Path p) {

		this.p = p;
	}

	@Override
	public void write(final OutputStream arg0) throws IOException, WebApplicationException {

		Files.copy(p, arg0);

	}

}
