
CREATE TABLE simpleRecords
(
    id BIGSERIAL not null constraint simplerecords_pkey primary key,
    recordName varchar(255),
    shortName varchar(255)
);

ALTER TABLE simpleRecords OWNER to dima;

SELECT * FROM simpleRecords;
INSERT INTO simpleRecords (recordName, shortName) VALUES ('Long record name','short record name');


