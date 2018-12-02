package com.start.web.service;

import com.start.web.component.MessageSearcher;
import com.start.web.domain.Message;
import com.start.web.domain.MessageSearch;
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
} 