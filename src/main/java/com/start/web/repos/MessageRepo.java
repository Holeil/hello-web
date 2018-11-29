package com.start.web.repos;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
    Page<Message> findAll(Pageable pageable);

    @Query(
            "from Message m order by m.title asc "
    )
    Iterable<Message> findByAuthor(User user);

}