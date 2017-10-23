package org.beprogramming.blogging.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class File {
	@Id
	@GeneratedValue
	private int		fileID;
	@Column
	private String	fileType;
	@Column
	private String	fileName;

	@Column
	private Long	fileSize;

	@Column
	private Date	created;
	@Lob
	private byte[]	image;

	public Date getCreated() {

		return created;
	}

	public int getFileID() {

		return fileID;
	}

	public String getFileName() {

		return fileName;
	}

	public Long getFileSize() {

		return fileSize;
	}

	public String getFileType() {

		return fileType;
	}

	public byte[] getImage() {

		return image;
	}

	public void setCreated(final Date created) {

		this.created = created;
	}

	public void setFileID(final int fileID) {

		this.fileID = fileID;
	}

	public void setFileName(final String fileName) {

		this.fileName = fileName;
	}

	public void setFileSize(final Long fileSize) {

		this.fileSize = fileSize;
	}

	public void setFileType(final String fileType) {

		this.fileType = fileType;
	}

	public void setImage(final byte[] image) {

		this.image = image;
	}
}
