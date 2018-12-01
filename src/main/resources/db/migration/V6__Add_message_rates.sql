create table message_rate (
  id int8 not null,
  user_id int8 not null references usr,
  message_id int8 not null references message,
  rate int4 not null,
  primary key(message_id, user_id)
);