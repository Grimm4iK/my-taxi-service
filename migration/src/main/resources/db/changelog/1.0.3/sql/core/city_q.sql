/*alter table city_queue drop column city_id;
alter table city_queue add primary key (name);*/
alter table city_queue add column port int;
ALTER TABLE city_queue ADD CONSTRAINT city_pk PRIMARY KEY (city_id);