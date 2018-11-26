package com.start.web.domain.util;

import com.start.web.domain.Theme;
import com.start.web.domain.User;

public abstract class UserHelper {
    public static boolean compareTwoUsers(User user1, User user2) {
        return user1.getId().equals(user2.getId());
    }

    public static Theme getThemeUser(User user) {
        if(user != null)
            return Theme.valueOf(user.getSiteDesign());
        else
            return Theme.valueOf("HUMANITARIAN");
    }

} 