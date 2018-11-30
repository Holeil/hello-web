package com.start.web.service;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TagService tagService;

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

    public List<Message> findByFilter(String filter, User user) {
        List<Message> messages = new ArrayList<>();

        List<Message> byTitle = messageRepo.findByTitleAndAuthor(filter, user);

        Iterable<Message> byTag = messageRepo.findByAuthor(user);

        List<Message> bySpecialty = messageRepo.findBySpecialtyAndAuthor(filter, user);

        for(Message message : byTitle) {
            if(!messages.contains(message)) {
                messages.add(message);
            }
        }

        for(Message message : bySpecialty) {
            if(!messages.contains(message)) {
                messages.add(message);
            }
        }

        for(Message message : byTag) {
            for(String tag : message.getTag().split("\\s+")) {
                if(tag.equals(filter) && !messages.contains(message)) {
                    messages.add(message);
                }
            }
        }

        return messages;
    }

    public void saveMessage(Message message) {
        tagService.addTagsInRepository(message.getTag());

        messageRepo.save(message);
    }

} 