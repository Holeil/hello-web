package com.start.web.service;

import com.start.web.domain.Tag;
import com.start.web.repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;

    public void addTagsInRepository(String tags) {
        for(String tag : tags.split("\\s+")) {
            Tag newTag = new Tag();

            tag = tag.toLowerCase();

            newTag.setId(tag);

            if(!tagRepo.findById(tag).isPresent()) {
                tagRepo.save(newTag);
            }
        }
    }
} 