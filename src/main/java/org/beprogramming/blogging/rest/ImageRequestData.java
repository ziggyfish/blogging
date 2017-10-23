package org.beprogramming.blogging.rest;

public class ImageRequestData {

	public Integer	id;

	public Integer	width;

	public Integer	height;

	public ImageRequestData(final Integer id, final Integer width, final Integer height) {

		this.id = id;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ImageRequestData other = (ImageRequestData) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + (height == null ? 0 : height.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (width == null ? 0 : width.hashCode());
		return result;
	}
}
