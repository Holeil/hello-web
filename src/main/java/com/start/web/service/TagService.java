package com.start.web.service;

import com.start.web.domain.Tag;
import com.start.web.repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepo tagRepo;

    public void addTagsInRepository(String tags) {
        for(String tag : tags.split("\\s+")) {
            Tag newTag = new Tag();

            newTag.setId(tag);

            if(!tagRepo.findById(tag).isPresent()) {
                tagRepo.save(newTag);
            }
        }
    }

    public List<Tag> findLastTags(int count) {
        List<Tag> arrTags = new ArrayList<>();

        List<Tag> tags = tagRepo.findAll();

        if(count >= tags.size()) {
            return tags;
        }

        for(int i = tags.size() - count; i < tags.size(); i++) {
            arrTags.add(tags.get(i));
        }

        return arrTags;

    }

} 