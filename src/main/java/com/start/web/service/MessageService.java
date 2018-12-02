package com.start.web.service;

import com.start.web.domain.Message;
import com.start.web.domain.MessageSearch;
import com.start.web.domain.User;
import com.start.web.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MessageRateService messageRateService;

    @Autowired
    private MessageSearchService messageSearchService;

    public void deleteMessage(Message message) {
        commentService.deleteCommentsForMessage(message);

        messageRateService.deleteMessageRateByMessage(message);

        messageRepo.delete(message);
    }

    public void deleteMessageByAuthor(User user) {
        Iterable<Message> messages = messageRepo.findByAuthor(user);

        for(Message message : messages) {
            commentService.deleteCommentsForMessage(message);

            messageRateService.deleteMessageRateByMessage(message);
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
        messageSearchService.saveMessage(new MessageSearch(messageRepo.save(message)));

        tagService.addTagsInRepository(message.getTag());
    }

    public List<Message> getTopMessages(int count) {
        Map<Float, List<Message>> rateAndMessages = new TreeMap<>();

        List<Message> messages = messageRepo.findAllAndSortById();


        // Find sum rate for all messages and add rate <-> message in HashTabel
        for(Message message : messages) {
            Float rate = messageRateService.countRate(message);

            if(rateAndMessages.containsKey(rate)) {
                rateAndMessages.get(rate).add(message);

            }
            else {
                List<Message> mess = new ArrayList<>();

                mess.add(message);

                rateAndMessages.put(rate, mess);
            }
        }

        List<Message> topMessage = new ArrayList<>();

        Set<Float> rates = rateAndMessages.keySet();

        //Get sorted messages and add in List from better interactions.
        if(messages.size() <= count) {
            for(int i = rates.size(); i > 0; i--) {
                topMessage.addAll(rateAndMessages.get(rates.toArray()[i - 1]));
            }

            return topMessage;
        }

        int iterator = 0;

        for(int i = rates.size(); i > rates.size() - count; i--) {
            if(iterator < count) {
                for(Message message : rateAndMessages.get(rates.toArray()[i-1])) {
                    if(iterator >= count) break;

                    topMessage.add(message);

                    iterator++;
                }
            }

            if(iterator >= count) break;
        }

        return topMessage;
    }

}