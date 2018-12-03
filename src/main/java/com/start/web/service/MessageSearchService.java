package com.start.web.service;

import com.start.web.component.MessageSearcher;
import com.start.web.domain.Comment;
import com.start.web.domain.CommentSearch;
import com.start.web.domain.Message;
import com.start.web.domain.MessageSearch;
import com.start.web.repos.CommentSearchRepo;
import com.start.web.repos.MessageSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageSearchService {
    @Autowired
    private MessageSearchRepo messageSearchRepo;

    @Autowired
    CommentSearchRepo commentSearchRepo;

    @Autowired
    private MessageSearcher messageSearcher;

    public List<MessageSearch> findAll() {
        return messageSearchRepo.findAll();
    }

    public Page<MessageSearch> search(String text, Pageable page) {
        return messageSearcher.searchMessages(text, page);
    }

    public MessageSearch saveMessage(MessageSearch message) {
        return messageSearchRepo.save(message);
    }

    public void deleteMessage(Message message) {
        commentSearchRepo.deleteAll(commentSearchRepo.findByMessageId(message.getId()));

        messageSearchRepo.delete(messageSearchRepo.findByMessageId(message.getId()));
    }

    public MessageSearch findMessageById(Long id) {
        return messageSearchRepo.findByMessageId(id);
    }

    public CommentSearch saveComment(CommentSearch commentSearch) {
        return commentSearchRepo.save(commentSearch);
    }

} 