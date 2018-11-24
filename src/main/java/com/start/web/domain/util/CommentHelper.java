package com.start.web.domain.util;

import com.start.web.domain.User;

public abstract class CommentHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
} 