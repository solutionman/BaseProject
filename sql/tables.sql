
CREATE TABLE simplerecords
(
    id BIGSERIAL not null constraint simplerecords_pkey primary key,
    record_name varchar(255),
    short_name varchar(255)
);

ALTER TABLE simplerecords OWNER to dima;

SELECT * FROM simplerecords;
INSERT INTO simplerecords (record_name, short_name) VALUES ('Long record name','short record name');


