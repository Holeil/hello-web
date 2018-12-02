package com.start.web.repos;

import com.start.web.domain.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepo extends CrudRepository<Tag, String> {
    List<Tag> findAll();
}