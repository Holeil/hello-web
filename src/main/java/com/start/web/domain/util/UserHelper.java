package com.start.web.domain.util;

import com.start.web.domain.enums.Language;
import com.start.web.domain.enums.Theme;
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

    public static Language getLangUser(User user) {
        if(user != null)
            return Language.valueOf(user.getLang());
        else
            return Language.valueOf("RU");
    }

} 