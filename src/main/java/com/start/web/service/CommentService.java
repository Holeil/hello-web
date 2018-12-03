package com.start.web.service;

import com.start.web.domain.*;
import com.start.web.domain.dto.CommentDto;
import com.start.web.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private MessageSearchService messageSearchService;

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

    public void saveComment(Message message, Comment comment) {
        MessageSearch ms = messageSearchService.findMessageById(message.getId());

        Comment newComment = commentRepo.save(comment);

        CommentSearch cs = new CommentSearch(newComment);

        ms.getComment().add(cs);

        messageSearchService.saveComment(cs);

    }
} 