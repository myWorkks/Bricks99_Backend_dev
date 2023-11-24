package com.marolix.Bricks99.service;

import java.util.List;

import com.marolix.Bricks99.dto.CommentDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface CommentService {
	public CommentDTO postCOmment(CommentDTO commentDTO) throws Bricks99Exception;

	public CommentDTO viewComment(Integer commentId) throws Bricks99Exception;

	public List<CommentDTO> viewCommentsOfProperty(Integer propertyId) throws Bricks99Exception;
}
