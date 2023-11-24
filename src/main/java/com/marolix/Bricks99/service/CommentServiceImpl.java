package com.marolix.Bricks99.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.marolix.Bricks99.dto.CommentDTO;
import com.marolix.Bricks99.dto.PropertyDetailsDTO;
import com.marolix.Bricks99.dto.UserDTO;
import com.marolix.Bricks99.entity.Comment;
import com.marolix.Bricks99.entity.PropertyDetails;
import com.marolix.Bricks99.entity.User;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.CommentRepository;
import com.marolix.Bricks99.repository.PropertyRepository;
import com.marolix.Bricks99.repository.UserRepository;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private Environment environment;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PropertyRepository properyRepository;

	@Override
	public CommentDTO postCOmment(CommentDTO commentDTO) throws Bricks99Exception {
		List<Comment> comments = commentRepository.findByUserUserId(commentDTO.getUserDTO().getUserId());
		for (Comment c : comments) {
			if (c.getProperty().getPropertyId().equals(commentDTO.getProperty().getPropertyId()))
				throw new Bricks99Exception(environment.getProperty("COMMENTSERVICE.ALREADY_COMMENTED"));
		}
		Comment comment = new Comment();
		comment.setComment(commentDTO.getComment());
		comment.setCommentedAt(LocalDateTime.now());
		Optional<User> optional = userRepository.findById(commentDTO.getUserDTO().getUserId());
		User user = optional.orElseThrow(() -> new Bricks99Exception("Invalid user or user not registered"));
		comment.setUser(user);
		Optional<PropertyDetails> optionalp = properyRepository.findById(commentDTO.getProperty().getPropertyId());
		PropertyDetails p = optionalp.orElseThrow(() -> new Bricks99Exception("property not found"));
		// System.out.println(p);
		comment.setProperty(p);

		Integer a = commentRepository.save(comment).getCommentId();
		commentDTO.setCommentId(a);
		return commentDTO;
	}

	@Override
	public CommentDTO viewComment(Integer commentId) throws Bricks99Exception {
		Optional<Comment> op = commentRepository.findById(commentId);
		Comment comment = op
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("COMMENT_SERVICE_NOT_FOUND")));

		return entityToDTO(comment);
	}

	private CommentDTO entityToDTO(Comment c) {
		CommentDTO d = new CommentDTO();
		d.setComment(c.getComment());
		d.setCommentedAt(c.getCommentedAt());
		d.setCommentId(c.getCommentId());
		PropertyDetailsDTO p = new PropertyDetailsDTO();
		p.setPropertyId(c.getProperty().getPropertyId());
		p.setPropertyPrice(c.getProperty().getPropertyPrice());
		d.setProperty(p);
		UserDTO u = new UserDTO();
		u.setUserId(c.getUser().getUserId());
		u.setFirstName(c.getUser().getFirstName());
		u.setEmail(c.getUser().getEmail());
		d.setUserDTO(u);
		return d;
	}

	@Override
	public List<CommentDTO> viewCommentsOfProperty(Integer propertyId) throws Bricks99Exception {
		List<Comment> comments = commentRepository.findByPropertyPropertyId(propertyId);
		if (comments.isEmpty())
			throw new Bricks99Exception(environment.getProperty("COMMENT_SERVICE_NOT_FOUND"));
		else

			return comments.stream().map(c -> entityToDTO(c)).collect(Collectors.toList());
	}
}
