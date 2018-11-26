insert into usr (id, username, password, active, lang, site_design, email)
    values (0, 'admin', '123', true, 'ru', 'MATHEMATICS', 'testhelloweb@mail.ru');

insert into user_role (user_id, roles)
    values (0, 'USER'), (0, 'ADMIN');
