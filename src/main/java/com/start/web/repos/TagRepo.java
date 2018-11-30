package com.start.web.repos;

import com.start.web.domain.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepo extends CrudRepository<Tag, String> {
}