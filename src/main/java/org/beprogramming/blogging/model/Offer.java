package org.beprogramming.blogging.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Offer {
	@Id
	@GeneratedValue
	private Integer	offerID;

	@Column
	private String	name;

	@Column
	private String	placement;

	@Lob
	private String	offerHTML;

	@Column
	private Integer	width;

	@Column
	private Integer	height;

	@Column
	private Integer	rotation;

	@Column
	private Boolean	enabled;

	public Boolean getEnabled() {

		return enabled;
	}

	public Integer getHeight() {

		return height;
	}

	public String getName() {

		return name;
	}

	public String getOfferHTML() {

		return offerHTML;
	}

	public Integer getOfferID() {

		return offerID;
	}

	public String getPlacement() {

		return placement;
	}

	public Integer getRotation() {

		return rotation;
	}

	public Integer getWidth() {

		return width;
	}

	public void setEnabled(final Boolean enabled) {

		this.enabled = enabled;
	}

	public void setHeight(final Integer height) {

		this.height = height;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public void setOfferHTML(final String offerHTML) {

		this.offerHTML = offerHTML;
	}

	public void setOfferID(final Integer offerID) {

		this.offerID = offerID;
	}

	public void setPlacement(final String placement) {

		this.placement = placement;
	}

	public void setRotation(final Integer rotation) {

		this.rotation = rotation;
	}

	public void setWidth(final Integer width) {

		this.width = width;
	}

}
