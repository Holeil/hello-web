package com.start.web.domain.util;

import com.start.web.domain.Role;
import com.start.web.domain.User;

public abstract class UserHelper {
    public static User setUserAdmin(User user) {
        user.getRoles().clear();
        user.getRoles().add(Role.valueOf("USER"));
        user.getRoles().add(Role.valueOf("ADMIN"));

        return user;
    }

    public static User setUserBlock(User user) {
        user.getRoles().clear();
        user.getRoles().add(Role.BLOCKED);

        return user;
    }

    public static User setUserUnblock(User user) {
        if(user.isRole("ADMIN")) {
            return user;
        }

        user.getRoles().clear();
        user.getRoles().add(Role.valueOf("USER"));

        return user;
    }

    public static boolean compareTwoUsers(User user1, User user2) {
        return user1.getId().equals(user2.getId());
    }
} 