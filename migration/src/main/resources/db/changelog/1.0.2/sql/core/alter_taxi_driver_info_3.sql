alter table taxi_drive_info add column if not exists busy boolean;

alter table taxi_drive_info add column if not exists city_id int;

alter table taxi_drive_info add constraint city_queue_id_fk foreign key (city_id) references city_queue (city_id);

alter table taxi_drive_info add column if not exists driver_rating int default 3 check (driver_rating > 0 AND driver_rating <= 5);

alter table taxi_drive_info drop column if exists car_model;

alter table taxi_drive_info add column if not exists car_id int;

alter table taxi_drive_info add constraint car_id_fk foreign key (car_id) references car (id);


