package org.beprogramming.blogging.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.beprogramming.blogging.model.File;
import org.beprogramming.blogging.model.Post;
import org.beprogramming.blogging.service.FileService;
import org.beprogramming.blogging.service.PostService;
import org.beprogramming.blogging.util.FileManager;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class FilePostUpload {

	private Integer		postID;

	@Inject
	private PostService	service;

	@Inject
	private FileService	fileService;

	public void delete(final File f) {

		final Post p = service.find(postID);
		for (final File i : p.getMediaFile()) {
			if (i.getFileID() != f.getFileID())
				continue;
			p.getMediaFile().remove(i);
			break;
		}
		service.update(p);

	}

	public List<File> getPostFileList() {

		return service.find(postID).getMediaFile();
	}

	public Integer getPostID() {

		return postID;
	}

	public void init(final Integer id) {

		postID = id;
	}

	public void setPostID(final Integer postID) {

		this.postID = postID;
	}

	public void upload(final FileUploadEvent event) throws IOException {

		final Post current = service.find(postID);
		final UploadedFile uploadFile = event.getFile();
		final File f = new File();
		f.setFileName(uploadFile.getFileName());
		f.setFileType(uploadFile.getContentType());
		f.setFileSize(uploadFile.getSize());
		fileService.create(f);
		FileManager.saveFile(f.getFileID(), uploadFile.getContents());
		if (current.getMediaFile() == null)
			current.setMediaFile(new ArrayList<File>());
		current.getMediaFile().add(f);
		service.update(current);
	}

}
