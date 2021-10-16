alter table taxi_drive_info add column if not exists  city_id bigint;

alter table taxi_drive_info add column if not exists rating int default 1 check (rating > 0 AND rating <= 5);

alter table taxi_drive_info add column if not exists available boolean default false;

alter table taxi_drive_info rename column car_model to car_id;

alter table taxi_drive_info alter column car_id type bigint using car_id::bigint;

alter table taxi_drive_info add constraint car_id_uq unique (car_id);

alter table taxi_drive_info add constraint taxi_drive_car foreign key (car_id) references car (id);

alter table taxi_drive_info add column minute_cost int default 10;

comment on column taxi_drive_info.minute_cost is 'Стоимость минуты';
comment on column taxi_drive_info.rating is 'Рейтинг водителя (от 1 до 5)';
comment on column taxi_drive_info.city_id is 'Таксопарк(Город) к которому прикреплен водитель';
comment on column taxi_drive_info.available is 'Занят ли водитель сейчас';
comment on column taxi_drive_info.car_id is'Идентификатор машины';