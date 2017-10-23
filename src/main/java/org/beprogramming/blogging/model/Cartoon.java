package org.beprogramming.blogging.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cartoon {

	@Id
	@GeneratedValue
	private int		storyID;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fileID")
	private File	mediaFile;

	@Column
	private String	caption;

	@Column
	private Date	created	= new Date();

	public String getCaption() {

		return caption;
	}

	public Date getCreated() {

		return created;
	}

	public File getMediaFile() {

		return mediaFile;
	}

	public int getStoryID() {

		return storyID;
	}

	public void setCaption(final String caption) {

		this.caption = caption;
	}

	public void setCreated(final Date created) {

		this.created = created;
	}

	public void setMediaFile(final File mediaFile) {

		this.mediaFile = mediaFile;
	}

	public void setStoryID(final int storyID) {

		this.storyID = storyID;
	}
}
