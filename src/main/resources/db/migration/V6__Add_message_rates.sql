create table message_rates (
  user_id int8 not null,
  message_id int8 not null,
  rate float not null,
  primary key(message_id, user_id)
);