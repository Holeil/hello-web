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

    @Query("select new com.start.web.domain.dto.CommentDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Comment m left join m.likes ml " +
            "where m.message = :message " +
            "group by m")
    List<CommentDto> findByMessage(@Param("message") Message message, @Param("user") User user);

    List<Comment> findByAuthor(User user);

} 