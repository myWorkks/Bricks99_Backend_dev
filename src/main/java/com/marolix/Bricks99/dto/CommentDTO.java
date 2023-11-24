package com.marolix.Bricks99.dto;

import java.time.LocalDateTime;



public class CommentDTO  {



	private Integer commentId;

	private String comment;
	private LocalDateTime commentedAt;

	private UserDTO userDTO;
	//@NotNull(message = "cannot be null")
	private PropertyDetailsDTO propertyDetailsDTO;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCommentedAt() {
		return commentedAt;
	}

	public void setCommentedAt(LocalDateTime commentedAt) {
		this.commentedAt = commentedAt;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO user) {
		this.userDTO = user;
	}

	public PropertyDetailsDTO getProperty() {
		return propertyDetailsDTO;
	}

	public void setProperty(PropertyDetailsDTO property) {
		this.propertyDetailsDTO = property;
	}

	@Override
	public String toString() {
		return "CommentDTO [commentId=" + commentId + ", comment=" + comment + ", commentedAt=" + commentedAt
				+ ", userDTO=" + userDTO + ", propertyDetailsDTO=" + propertyDetailsDTO + "]";
	}

}
