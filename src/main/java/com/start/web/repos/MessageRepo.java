package com.start.web.repos;

import com.start.web.domain.Message;
import com.start.web.domain.MessageRate;
import com.start.web.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    Page<Message> findAll(Pageable pageable);

    Iterable<Message> findByAuthor(User user);

    @Query(
            "from Message m where m.author = :user order by m.title asc "
    )
    Iterable<Message> sortAuthorMessageByTitle(@Param("user") User user);

    @Query(
            "from Message m where m.author = :user order by m.date desc "
    )
    Iterable<Message> sortAuthorMessageByDate(@Param("user") User user);

    List<Message> findBySpecialtyAndAuthor(String specialty, User user);

    List<Message> findByTitleAndAuthor(String title, User user);

}