CREATE SEQUENCE IF NOT EXISTS grade_seq START 1;

CREATE TABLE IF NOT EXISTS driver_rating (
    id BIGINT NOT NULL DEFAULT nextval('grade_seq' :: regclass),
    driver_id BIGINT,
    rating INT DEFAULT 1 CHECK (rating > 0 AND rating <= 5),
    order_id BIGINT,

    CONSTRAINT grade_taxi_id_pk
        primary key (id),
    CONSTRAINT grade_driver_id_fk FOREIGN KEY (driver_id)
        REFERENCES taxi_drive_info (driver_id),
    CONSTRAINT grade_order_id_fk FOREIGN KEY  (order_id)
        REFERENCES orders (id)
);

COMMENT ON TABLE driver_rating IS 'Информация о отзывах водителей';

COMMENT ON COLUMN driver_rating.driver_id IS 'Идентификатор водителя';
COMMENT ON COLUMN driver_rating.rating IS 'Оценка клиента за поездку';