package com.start.web.repos;

import com.start.web.domain.MessageRate;
import org.springframework.data.repository.CrudRepository;

public interface MessageRateRepo extends CrudRepository<MessageRate, String> {
    Iterable<MessageRate> findByMessageIdAndUserId(Long messageId, Long userId);

    Iterable<MessageRate> findByMessageId(Long messageId);

    Iterable<MessageRate> findByUserId(Long userId);

}