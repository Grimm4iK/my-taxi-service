create sequence if not exists order_total_seq
    start 1;

create table if not exists order_total
(
    order_id bigint not null default nextval('order_total_seq' :: regclass),
    sum      int
);

comment on table order_total is 'Сводная информация по заказам';
comment on column order_total.order_id is 'Идентификатор заказа';
comment on column order_total.sum is 'Сумма заказа';

alter table order_total
    add constraint order_id_fk foreign key (order_id)
        references orders (order_id);
