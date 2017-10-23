package org.beprogramming.blogging.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Cacheable(true)
public class Category {

	@Id
	@GeneratedValue
	private int		categoryID;

	@Column
	private String	name;

	public int getCategoryID() {

		return categoryID;
	}

	public String getName() {

		return name;
	}

	public void setCategoryID(final int categoryID) {

		this.categoryID = categoryID;
	}

	public void setName(final String name) {

		this.name = name;
	}

	@Override
	public String toString() {

		return "Category [categoryID=" + categoryID + "]";
	}
}
