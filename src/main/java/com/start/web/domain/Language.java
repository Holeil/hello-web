package com.start.web.domain;

public enum  Language {
    RU("Вход", "Имя пользователя", "Пароль", "Email", "Регистрация", "Регистрация нового пользователя", "Создать", "Выйти",
            "Новый конспект", "Название конспекта", "Тэги", "Номер специальности", "Короткое описание", "Обновить", "Редактировать",
            "Удалить", "Заблокировать", "Разблокировать", "Сделать Админом", "Автор", "Выбрать всех",
            "Выбрать пользователя", "Роли", "Переключить тему", "Список пользователей", "Действия",
            "Информация о пользователе", "Страницы", "Повторите пароль", "Беларускі", "Отправить комментарий", "Ваш комментарий", "Пользователь"),

    BY("Уваход", "Імя карыстальніка", "Пароль", "Email", "Рэгістрацыя", "Зарэгістравацца", "Стварыць", "Выйсці",
            "Новы канспект", "Назва канпекта", "Тэгі", "Нумар спецыяльнасці", "Кароткае апісанне", "Абнавіць", "Рэдагаваць", "Выдаліць",
            "Заблакаваць", "Разблакаваць", "Зрабіць Адмінам", "Аўтар", "Выбраць ўсіх", "Выбраць карыстальніка", "Ролі",
            "Пераключыць тэму", "Спіс карыстальнікаў", "Дзеянні", "Інфармацыя пра карыстальніка", "Старонкі",
            "Паўтарыце пароль", "Русский", "Адправіць каментарый", "Ваш каментарый", "Карыстальнік");

    private String login;
    private String username;
    private String password;
    private String email;
    private String registration;
    private String addNewUser;
    private String create;
    private String logout;
    private String addMessage;
    private String title;
    private String tags;
    private String specialty;
    private String text;
    private String update;
    private String edit;
    private String delete;
    private String blocked;
    private String unblocked;
    private String setAdmin;
    private String author;
    private String chooseAll;
    private String chooseUser;
    private String roles;
    private String changeTheme;
    private String userList;
    private String action;
    private String userInform;
    private String pages;
    private String retypePassword;
    private String changeLang;
    private String sendComment;
    private String youComment;
    private String user;

    private Language(String login, String username, String password, String email, String registration, String addNewUser,
                     String create, String logout, String addMessage, String title, String tags, String specialty,
                     String text, String update, String edit, String delete, String blocked, String unblocked,
                     String setAdmin, String author, String chooseAll, String chooseUser, String roles, String changeTheme,
                     String userList, String action, String userInform, String pages, String retypePassword,
                     String changeLang, String sendComment, String youComment, String user) {
        this.login = login;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registration = registration;
        this.addNewUser = addNewUser;
        this.create = create;
        this.logout = logout;
        this.addMessage = addMessage;
        this.title = title;
        this.tags = tags;
        this.specialty = specialty;
        this.text = text;
        this.update = update;
        this.edit = edit;
        this.delete = delete;
        this.blocked = blocked;
        this.unblocked = unblocked;
        this.setAdmin = setAdmin;
        this.author = author;
        this.chooseAll = chooseAll;
        this.chooseUser = chooseUser;
        this.roles = roles;
        this.changeTheme = changeTheme;
        this.userList = userList;
        this.action = action;
        this.userInform = userInform;
        this.pages = pages;
        this.retypePassword = retypePassword;
        this.changeLang = changeLang;
        this.sendComment = sendComment;
        this.youComment = youComment;
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRegistration() {
        return registration;
    }

    public String getAddNewUser() {
        return addNewUser;
    }

    public String getCreate() {
        return create;
    }

    public String getLogout() {
        return logout;
    }

    public String getAddMessage() {
        return addMessage;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getText() {
        return text;
    }

    public String getUpdate() {
        return update;
    }

    public String getEdit() {
        return edit;
    }

    public String getDelete() {
        return delete;
    }

    public String getBlocked() {
        return blocked;
    }

    public String getUnblocked() {
        return unblocked;
    }

    public String getSetAdmin() {
        return setAdmin;
    }

    public String getAuthor() {
        return author;
    }

    public String getChooseAll() {
        return chooseAll;
    }

    public String getChooseUser() {
        return chooseUser;
    }

    public String getRoles() {
        return roles;
    }

    public String getChangeTheme() {
        return changeTheme;
    }

    public String getUserList() {
        return userList;
    }

    public String getAction() {
        return action;
    }

    public String getUserInform() {
        return userInform;
    }

    public String getPages() {
        return pages;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public String getChangeLang() {
        return changeLang;
    }

    public String getSendComment() {
        return sendComment;
    }

    public String getYouComment() {
        return youComment;
    }

    public String getUser() {
        return user;
    }
}