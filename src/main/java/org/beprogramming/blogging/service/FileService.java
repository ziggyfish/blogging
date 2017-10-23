package org.beprogramming.blogging.service;

import java.util.Date;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;

import org.beprogramming.blogging.model.File;
import org.beprogramming.std.CrudService;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class FileService extends CrudService<File> {

	@Override
	public void create(final File item) {

		item.setCreated(new Date());
		super.create(item);
	}

	@Override
	public File find(final Object id) {

		return super.find(id);
	}

	@Override
	protected Class<File> getTypeClass() {

		return File.class;
	}

}
