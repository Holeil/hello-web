package com.start.web.repos;

import com.start.web.domain.Comment;
import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.dto.CommentDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepo extends CrudRepository<Comment, Long> {

    @Query("select new com.start.web.domain.dto.CommentDto( " +
            "c, " +
            "count(ml), " +
            "sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Comment c left join c.likes ml " +
            "where c.message = :message " +
            "group by c " +
            "order by c.id asc")
    List<CommentDto> findByMessage(@Param("message") Message message, @Param("user") User user);

    List<Comment> findByAuthor(User user);

} 