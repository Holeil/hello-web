package com.start.web.domain;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Indexed
@Entity
public class MessageSearch {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long messageId;

    @Field
    private String title;

    @Field
    private String specialty;

    @Field
    private String text;

    @Field
    private String tag;

    @IndexedEmbedded
    @ManyToMany
    private Set<CommentSearch> comment = new HashSet<>();

    public MessageSearch() {}

    public MessageSearch(Message message) {
        messageId = message.getId();
        title = message.getTitle();
        text = message.getText();
        tag = message.getTag();
        specialty = message.getSpecialty();
    }

    public Set<CommentSearch> getComment() {
        return comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setComment(Set<CommentSearch> comment) {
        this.comment = comment;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}