package org.beprogramming.blogging.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.beprogramming.blogging.rest.ImageRequestData;

public class FileManager {

	private static String FILE_NAME = "orginal";

	public static Path getCache(final ImageRequestData data) {

		final java.nio.file.Path p = getCacheFile(data);
		if (!Files.exists(p))
			return null;

		return p;

	}

	public static Path getCacheDirectory(final ImageRequestData request) {

		return getDirectoryForFile(request.id);
	}

	public static Path getCacheFile(final ImageRequestData request) {

		String filename = "";
		if (request.width != null)
			filename += "w" + request.width;
		if (request.height != null)
			filename += "h" + request.height;
		if (request.height == null && request.width == null)
			filename = null;
		if (filename == null)
			filename = FILE_NAME;
		else
			filename = filename + ".png";
		return Paths.get(getCacheDirectory(request).toString(), filename);
	}

	public static Path getDirectoryForFile(final Integer fileID) {

		return Paths.get("/home2/zanettis/public_html", "zanetti", fileID.toString());
	}

	public static InputStream getFileData(final Integer fileID) throws FileNotFoundException {

		final Path path = Paths.get(getDirectoryForFile(fileID).toString(), FILE_NAME);

		return new FileInputStream(path.toFile());
	}

	public static void putCache(final ImageRequestData request, final ByteArrayOutputStream imageData) {

		try {
			final java.nio.file.Path p = getCacheDirectory(request).toAbsolutePath();
			Files.createDirectories(p);

			Files.write(getCacheFile(request), imageData.toByteArray(), StandardOpenOption.CREATE);
		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void saveFile(final int fileID, final byte[] data) throws IOException {

		final Path path = getDirectoryForFile(fileID);
		Files.createDirectories(path);
		Files.write(Paths.get(path.toString(), FILE_NAME), data, StandardOpenOption.CREATE);
	}
}
