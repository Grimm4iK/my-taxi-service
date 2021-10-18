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

comment on table orders is 'Заказы';
comment on column orders.id is 'Идентификатор заказа';
comment on column orders.client_id is 'Идентификатор клиента';
comment on column orders.driver_id is 'Идентификатор водителя';

alter table orders
    add constraint order_driver_id_fk foreign key (driver_id)
        references taxi_drive_info (driver_id);
