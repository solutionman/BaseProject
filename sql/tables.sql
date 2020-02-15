
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

INSERT INTO users VALUES (1,'user','$2a$11$vwr8793lnCpvfD5T6/K16e0PXvTXGa0xE37Uez7WQJWoT4Wg5FN6K'); --12345678

INSERT INTO roles VALUES ( 1, 'ROLE_USER' );
INSERT INTO roles VALUES ( 2, 'ROLE_ADMIN' );
INSERT INTO roles VALUES ( 3, 'ROLE_GUEST' );
INSERT INTO roles VALUES ( 4, 'ROLE_PERFORMER' );

INSERT INTO user_roles values (1,1);



