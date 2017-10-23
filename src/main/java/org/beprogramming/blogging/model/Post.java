package org.beprogramming.blogging.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Cacheable
public class Post {

	@Id
	@GeneratedValue
	private int			postID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userID")
	private User		author;

	@Column
	private String		title;

	@Column
	private String		subtitle;

	@Lob
	private String		abs;

	@ManyToMany
	@JoinTable(name = "PostFiles", joinColumns = { @JoinColumn(name = "PostID", referencedColumnName = "postID") }, inverseJoinColumns = { @JoinColumn(name = "FileID", referencedColumnName = "fileID") })
	private List<File>	mediaFile;

	@Column
	private String		mediaCaption;

	@Lob
	private String		content;

	@Column
	private Boolean		isOpinion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryId")
	private Category	category;

	@Column
	private Date		created;

	@Column
	private Boolean		published		= false;

	@Column
	private Integer		NumberOfVisits	= 0;

	@Column(length = 1024)
	private String		keywords;

	private String		genres;

	public String getAbs() {

		return abs;
	}

	public User getAuthor() {

		return author;
	}

	public Category getCategory() {

		return category;
	}

	public String getContent() {

		return content;
	}

	public Date getCreated() {

		return created;
	}

	public String getGenres() {

		return genres;
	}

	public Boolean getIsOpinion() {

		return isOpinion;
	}

	public String getKeywords() {

		return keywords;
	}

	public String getMediaCaption() {

		return mediaCaption;
	}

	public List<File> getMediaFile() {

		return mediaFile;
	}

	public Integer getNumberOfVisits() {

		return NumberOfVisits;
	}

	public int getPostID() {

		return postID;
	}

	public Boolean getPublished() {

		return published;
	}

	public String getSubtitle() {

		return subtitle;
	}

	public String getTitle() {

		return title;
	}

	public void setAbs(final String abs) {

		this.abs = abs;
	}

	public void setAuthor(final User author) {

		this.author = author;
	}

	public void setCategory(final Category category) {

		this.category = category;
	}

	public void setContent(final String content) {

		this.content = content;
	}

	public void setCreated(final Date created) {

		this.created = created;
	}

	public void setGenres(final String genres) {

		this.genres = genres;
	}

	public void setIsOpinion(final Boolean isOpinion) {

		this.isOpinion = isOpinion;
	}

	public void setKeywords(final String keywords) {

		this.keywords = keywords;
	}

	public void setMediaCaption(final String mediaCaption) {

		this.mediaCaption = mediaCaption;
	}

	public void setMediaFile(final List<File> mediaFile) {

		this.mediaFile = mediaFile;
	}

	public void setNumberOfVisits(final Integer numberOfVisits) {

		NumberOfVisits = numberOfVisits;
	}

	public void setPostID(final int postID) {

		this.postID = postID;
	}

	public void setPublished(final Boolean published) {

		this.published = published;
	}

	public void setSubtitle(final String subtitle) {

		this.subtitle = subtitle;
	}

	public void setTitle(final String title) {

		this.title = title;
	}

	@Override
	public String toString() {

		return "Post [postID=" + postID + "]";
	}

}
