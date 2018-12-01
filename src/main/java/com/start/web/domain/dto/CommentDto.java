package com.start.web.domain.dto;

import com.start.web.domain.Comment;
import com.start.web.domain.Message;
import com.start.web.domain.User;
import com.start.web.domain.util.CommentHelper;

public class CommentDto {
    private Long id;
    private String text;
    private String date;
    private User author;
    private Message message;
    private Long likes;
    private Boolean meLiked;

    public CommentDto(Comment comment, Long likes, Boolean meLiked) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.date = comment.getDate();
        this.author = comment.getAuthor();
        this.message = comment.getMessage();
        this.likes = likes;
        this.meLiked = meLiked;
    }

    public String getAuthorName() {
        return CommentHelper.getAuthorName(author);
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public User getAuthor() {
        return author;
    }

    public Message getMessage() {
        return message;
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

}