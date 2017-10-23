package org.beprogramming.blogging.service;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Stateless;

import org.beprogramming.blogging.model.Comment;

@Stateless
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class UploadCommentService extends Comment {

	public int retrievePostID() {

		return 0;
	}
}
