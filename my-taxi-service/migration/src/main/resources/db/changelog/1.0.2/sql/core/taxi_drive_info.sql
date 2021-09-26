create sequence if not exists taxi_driver_seq
    start 1;

create table if not exists taxi_drive_info
(
    driver_id   bigint not null          default nextval('taxi_driver_seq' :: regclass),
    last_name   text   not null,
    first_name  text   not null,
    middle_name text,
    level       int    not null,
    car_model   text   not null,
    create_dttm timestamp with time zone default now() not null,
    constraint taxi_drive_info_id_pk
        primary key (driver_id)
);

comment on table taxi_drive_info is 'Информация о водителях такси';

comment on column taxi_drive_info.driver_id is 'Идентификатор водителя';
comment on column taxi_drive_info.last_name is 'Фамилия водителя';
comment on column taxi_drive_info.first_name is 'Имя водителя';
comment on column taxi_drive_info.middle_name is 'Отчество водителя';
comment on column taxi_drive_info.level is 'Уровень водителя';
comment on column taxi_drive_info.car_model is 'Модель автомобиля';
comment on column taxi_drive_info.create_dttm is 'Дата внесения информации о водителе';



