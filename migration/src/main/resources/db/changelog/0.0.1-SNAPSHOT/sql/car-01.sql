create sequence car_seq start 1;

create table if not exists car
(

    id         bigint      not null default (nextval('car_seq')),
    model      varchar(100),
    createDttm timestamptz not null default current_timestamp
);

alter table car
    add constraint car_pk primary key (id);
