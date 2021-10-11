alter table orders add column if not exists start_trip timestamp;
alter table orders add column if not exists end_trip timestamp;