package com.start.web.repos;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);

    List<Message> findByAuthor(User user);

}