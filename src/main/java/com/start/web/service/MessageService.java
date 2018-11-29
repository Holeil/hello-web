package com.start.web.service;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private CommentService commentService;

    public void deleteMessage(Message message) {
        commentService.deleteCommentsForMessage(message);

        messageRepo.delete(message);
    }

    public void deleteMessageByAuthor(User user) {
        Iterable<Message> messages = messageRepo.findByAuthor(user);

        for(Message message : messages) {
            commentService.deleteCommentsForMessage(message);
        }

        messageRepo.deleteAll(messages);
    }

} 