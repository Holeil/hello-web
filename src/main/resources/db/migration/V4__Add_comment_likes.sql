create table comment_likes (
  user_id int8 not null references usr,
  comment_id int8 not null references comment,
  primary key (user_id, comment_id)
);