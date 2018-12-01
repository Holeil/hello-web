package com.start.web.domain;

import javax.persistence.*;

@Entity
@Table(name = "message_rate")
public class MessageRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long messageId;
    private Integer rate;

    public MessageRate() {}

    public MessageRate(Long userId, Long messageId, Integer rate) {
        this.userId = userId;
        this.messageId = messageId;
        this.rate = rate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}