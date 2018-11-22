package com.start.web.domain.util;

import com.start.web.domain.Role;
import com.start.web.domain.User;
import com.start.web.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

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
} 