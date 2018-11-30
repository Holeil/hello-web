package com.start.web.domain;

public class MessageRate {
    private Long messageId;
    private Long userId;
    private Float rate;

    public MessageRate() {}

    public MessageRate(Long messageId, Long userId, Float rate) {
        this.messageId = messageId;
        this.userId = userId;
        this.rate = rate;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}