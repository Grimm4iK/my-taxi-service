create sequence if not exists city_queue_seq
    start 1;

create table if not exists city_queue
(
    city_id bigint      not null default nextval('city_queue_seq' :: regclass),
    name    text        not null,
    queue   varchar(30) not null
);

comment on table city_queue is 'Очереди';
comment on column city_queue.city_id is 'Идентификатор очереди';
comment on column city_queue.name is 'Название города';
comment on column city_queue.queue is 'Название очереди';

alter table city_queue
    add constraint city_queue_uq unique (name, queue);
