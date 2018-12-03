package com.start.web.repos;

import com.start.web.domain.CommentSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentSearchRepo extends JpaRepository<CommentSearch, String> {
    List<CommentSearch> findByMessageId(Long id);

} 