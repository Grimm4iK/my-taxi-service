create sequence if not exists order_seq start 1;

create table if not exists orders
(

    id         bigint      not null default (nextval('order_seq')),
    client_id  bigint references user_account(id),
    trip_start timestamp,
    trip_end timestamp,
    driver_id bigint references taxi_drive_info(driver_id),
    amount double precision,
    rating int,
    primary key (id)
);
