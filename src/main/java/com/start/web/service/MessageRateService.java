package com.start.web.service;

import com.start.web.domain.Message;
import com.start.web.domain.MessageRate;
import com.start.web.repos.MessageRateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageRateService {
    @Autowired
    private MessageRateRepo messageRateRepo;

    public void changeRate(MessageRate messageRate, Integer rate) {
        messageRate.setRate(rate);

        messageRateRepo.save(messageRate);
    }

    public Float countRate(Message message) {
        Iterable<MessageRate> messageRates = messageRateRepo.findByMessageId(message.getId());

        float count = 0;
        float sumRate = 0;

        for(MessageRate messageRate : messageRates) {
            count++;
            sumRate += messageRate.getRate();
        }

        if(count == 0 ) return (float)0;

        return sumRate / count;
    }

    public void deleteMessageRate(Message message) {
        Iterable<MessageRate> messageRates = messageRateRepo.findByMessageId(message.getId());

        messageRateRepo.deleteAll(messageRates);
    }

} 