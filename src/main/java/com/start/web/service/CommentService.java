package com.start.web.service;

import com.start.web.domain.Comment;
import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.dto.CommentDto;
import com.start.web.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;

    public void deleteCommentsForMessage(Message message) {
        List<CommentDto> comments = commentRepo.findByMessage(message, null);

        for(CommentDto comment : comments) {
            commentRepo.deleteById(comment.getId());
        }

    }

    public void deleteUserComment(User user) {
        commentRepo.deleteAll(commentRepo.findByAuthor(user));
    }

    public void removeUserLikes(User user) {
        Iterable<Comment> comments = commentRepo.findAll();

        for(Comment comment : comments) {
            comment.getLikes().remove(user);
        }
    }
} 