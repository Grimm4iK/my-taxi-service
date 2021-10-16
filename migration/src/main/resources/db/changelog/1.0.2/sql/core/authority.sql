create table if not exists authority
(
    id              bigserial primary key,
    user_account_id bigint      not null,
    authority       varchar(64) not null
);

alter table authority
    add constraint authority_user_account_fk foreign key (user_account_id)
        references user_account (id);

create index if not exists authority_ux
    on authority using hash (user_account_id)