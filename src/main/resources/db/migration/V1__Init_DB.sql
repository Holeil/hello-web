create sequence hibernate_sequence start 1 increment 1;

create table message (
    id int8 not null,
    title varchar (255) not null,
    specialty varchar(255) not null,
    text varchar(2048) not null,
    date varchar (255) not null,
    tag varchar(255),
    user_id int8,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    lang varchar (255),
    site_design varchar (255),
    info varchar (600),
    primary key (id)
);

create table comment (
  id int8 not null,
  text varchar (255),
  date varchar (255),
  user_id int8,
  message_id int8,
  primary key (id)
);

alter table if exists comment
    add constraint comment_message_fk
    foreign key (message_id) references message;

alter table if exists comment
    add constraint comment_user_fk
    foreign key (user_id) references usr;

alter table if exists message
    add constraint message_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
foreign key (user_id) references usr;