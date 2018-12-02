create table message_search (
  id int8 not null,
  message_id int8 not null,
  title varchar (255) not null,
  text varchar (2048) not null,
  tag varchar (255) not null,
  primary key(id)
);