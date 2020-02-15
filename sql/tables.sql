
CREATE TABLE simplerecords
(
    id BIGSERIAL not null constraint simplerecords_pkey primary key,
    record_name varchar(255),
    short_name varchar(255)
);

ALTER TABLE simplerecords OWNER to dima;

SELECT * FROM simplerecords;
INSERT INTO simplerecords (record_name, short_name) VALUES ('Long record name','short record name');


create table users
(
    id           serial not null,
    username     varchar(255),
    password     varchar(255),
    nickname     varchar(200)
);
alter table users owner to dima;

create sequence roles_id_seq;
create table roles
(
    id   integer default nextval('public.roles_id_seq'::regclass) not null
        constraint roles_pkey1
            primary key,
    name varchar(100)                                             not null
);
alter table roles owner to dima;

create table user_roles
(
    user_id integer not null,
    role_id integer not null
        constraint user_roles_ibfk_2
            references roles,
    constraint user_id
        unique (user_id, role_id)
);
alter table user_roles owner to dima;



