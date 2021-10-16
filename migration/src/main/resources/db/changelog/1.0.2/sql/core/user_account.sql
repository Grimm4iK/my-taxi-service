create table if not exists user_account
(
    id       bigserial primary key,
    login    varchar(100) unique not null,
    password varchar(512)
);