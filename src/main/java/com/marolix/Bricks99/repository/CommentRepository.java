package com.marolix.Bricks99.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marolix.Bricks99.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByUserUserId(Integer userId);

	List<Comment> findByPropertyPropertyId(Integer propertyId);

}
