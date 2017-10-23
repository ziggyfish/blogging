package org.beprogramming.blogging.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.Cartoon;
import org.beprogramming.blogging.model.File;
import org.beprogramming.blogging.service.CartoonService;
import org.beprogramming.blogging.service.FileService;
import org.beprogramming.blogging.util.Utility;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class CartoonController {

	@Inject
	public CartoonService	service;

	@Inject
	private FileService		fileService;

	private List<Cartoon>	list;

	private Cartoon			latest	= null;

	public void deleteCartoon(final Cartoon c) {

		service.deleteById(c.getStoryID());
	}

	public String getCartoonURL(final Cartoon c) {

		return Utility.getCartoonURL(c);
	}

	public Cartoon getLatestCartoon() {

		if (latest == null)
			latest = service.getLatestCartoon();
		return latest;
	}

	public List<Cartoon> getList() {

		if (list == null)
			list = service.findAll();
		return list;
	}

	public void save(final Cartoon c) {

		service.update(c);
	}

	public void upload(final FileUploadEvent event) {

		final Cartoon current = new Cartoon();
		final UploadedFile uploadFile = event.getFile();
		final File f = new File();
		f.setFileName(uploadFile.getFileName());
		f.setFileType(uploadFile.getContentType());
		f.setFileSize(uploadFile.getSize());
		f.setImage(uploadFile.getContents());
		fileService.create(f);
		current.setMediaFile(f);
		service.update(current);
		list = null;
	}

}
