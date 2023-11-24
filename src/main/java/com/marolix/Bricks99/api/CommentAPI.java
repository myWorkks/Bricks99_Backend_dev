package com.marolix.Bricks99.api;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marolix.Bricks99.dto.CommentDTO;
import com.marolix.Bricks99.dto.PropertyDetailsDTO;
import com.marolix.Bricks99.dto.UserDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.CommentService;

@RestController
@RequestMapping(value = "comment-api")
@Validated
public class CommentAPI {

	@Autowired
	private CommentService commentService;

//	@PostMapping(value = "add-comment")
//	public ResponseEntity<CommentDTO> giveComment(HttpServletRequest request) throws Bricks99Exception {
//		// System.out.println(commentDTO);
//		Enumeration<String> e = request.getParameterNames();
//		CommentDTO dto = new CommentDTO();
//
//		dto.setComment(request.getParameter("comment"));
//		UserDTO user = new UserDTO();
//		user.setUserId(Integer.valueOf(request.getParameter("userDTO.userId")));
//		dto.setUserDTO(user);
//		PropertyDetailsDTO pdto = new PropertyDetailsDTO();
//		pdto.setPropertyId(Integer.valueOf(request.getParameter("propertyDetailsDTO.propertyId")));
//		dto.setProperty(pdto);
//
//		return new ResponseEntity<CommentDTO>(commentService.postCOmment(dto), HttpStatus.CREATED);
//	}
	@PostMapping(value = "add-comment")
	public ResponseEntity<CommentDTO> giveComment(@RequestBody CommentDTO dto) throws Bricks99Exception {

		return new ResponseEntity<CommentDTO>(commentService.postCOmment(dto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/view-comment/commentId/{commentId}")
	public ResponseEntity<CommentDTO> viewComment(@PathVariable Integer commentId) throws Bricks99Exception {
		return new ResponseEntity<CommentDTO>(commentService.viewComment(commentId), HttpStatus.OK);
	}

	@GetMapping(value = "/view-comments/propertyId/{propertyId}")
	public ResponseEntity<List<CommentDTO>> viewCommentsOfProperty(@PathVariable Integer propertyId)
			throws Bricks99Exception {
		return new ResponseEntity<List<CommentDTO>>(commentService.viewCommentsOfProperty(propertyId), HttpStatus.OK);
	}
}
