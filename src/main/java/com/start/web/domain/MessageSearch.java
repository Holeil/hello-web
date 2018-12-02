package com.start.web.domain;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
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
    private String text;

    @Field
    private String tag;

    public MessageSearch() {}

    public MessageSearch(Message message) {
        messageId = message.getId();
        title = message.getTitle();
        text = message.getText();
        tag = message.getTag();
    }

} 