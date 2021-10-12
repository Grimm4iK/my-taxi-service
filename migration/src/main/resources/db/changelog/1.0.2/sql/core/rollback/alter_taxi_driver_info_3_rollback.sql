alter table taxi_drive_info drop column if exists busy;
alter table taxi_drive_info drop column if exists city_id;
alter table taxi_drive_info drop column if exists driver_rating;

alter table taxi_drive_info drop column if exists car_id;
alter table taxi_drive_info add column if not exists car_model varchar;
