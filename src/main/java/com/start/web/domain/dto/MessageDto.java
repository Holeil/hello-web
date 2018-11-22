package com.start.web.domain.dto;

import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.util.MessageHelper;

public class MessageDto {
    private Long id;
    private String title;
    private String specialty;
    private String text;
    private String tag;
    private User author;
    private Long likes;
    private Boolean meLiked;

    public MessageDto(Message message, Long likes, Boolean meLiked) {
        this.id = message.getId();
        this.text = message.getText();
        this.tag = message.getTag();
        this.title = message.getTitle();
        this.author = message.getAuthor();
        this.specialty = message.getSpecialty();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public User getAuthor() {
        return author;
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", meLiked=" + meLiked +
                '}';
    }
}