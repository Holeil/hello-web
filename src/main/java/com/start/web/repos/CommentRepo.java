package com.start.web.repos;

import com.start.web.domain.Comment;
import com.start.web.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {
    List<Comment> findByMessage(Message message);
} 