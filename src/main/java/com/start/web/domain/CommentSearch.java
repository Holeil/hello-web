package com.start.web.domain;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Indexed
public class CommentSearch {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "message_search_id")
    private Long messageId;

    @Field
    @Column(name = "text")
    private String text;

    public CommentSearch() {}

    public CommentSearch(Comment comment) {
        messageId = comment.getMessage().getId();
        commentId = comment.getId();
        text = comment.getText();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}