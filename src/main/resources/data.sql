DROP TABLE IF EXISTS ward;
DROP TABLE IF EXISTS trainer;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

-- https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/appendix-schema.html
create table users
(
    id       uuid PRIMARY KEY,
    username varchar(50)  not null UNIQUE,
    password varchar(100) not null,
    enabled  boolean      not null
);

create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references
        users (username)
);

insert into users (id, username, password, enabled)
        --Trainer --
values ('887f4ba5-9936-4ef6-9969-5974ee1a79f8', 'Adam','{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true),
       --Ward--
       ('b60bfc9f-7cbe-4127-942e-8f564e2f3fd4', 'Jan','{bcrypt}$2a$10$upzXFsFUOClFRR69OMKF8eajGMRs0vhcSHqvNDKy9yfW45w7o9z6O', true);

insert into authorities (username, authority)
values ('Adam', 'USER'), ('Jan', 'USER');

CREATE TABLE trainer
(
    id_trainer   uuid PRIMARY KEY,
    name         VARCHAR(250) NOT NULL,
    surname      VARCHAR(250) NOT NULL,
    code_trainer VARCHAR(200) NOT NULL
);

CREATE TABLE ward
(
    id_ward    uuid PRIMARY KEY,
    name       VARCHAR(250) NOT NULL,
    surname    VARCHAR(250) NOT NULL,
    id_trainer uuid         NULL
);

INSERT INTO trainer (id_trainer, name, surname, code_trainer)
VALUES ('887f4ba5-9936-4ef6-9969-5974ee1a79f8', 'Adam', 'Zakrzewski', 'AZ123'),
       ('2f5b8fa5-45ff-461e-9ef1-182414052a21', 'Szymon', 'Kot', 'SK123'),
       ('32ef3937-3399-4a51-97e0-3045f180f3d2', 'Jan', 'Lotek', 'JL123');

INSERT INTO ward (id_ward, name, surname, id_trainer)
VALUES ('ad95813b-bc73-4e33-9adc-48e5af7dd910', 'Adam', 'Majutek', '887f4ba5-9936-4ef6-9969-5974ee1a79f8'),
       ('f38c1bba-711e-44eb-a6c4-b8ad061f918c', 'Zbyszek', 'Sowa', '2f5b8fa5-45ff-461e-9ef1-182414052a21'),
       ('b60bfc9f-7cbe-4127-942e-8f564e2f3fd4', 'Jan', 'Grycan', '32ef3937-3399-4a51-97e0-3045f180f3d2'),
       ('85e75e35-563f-4cf0-8ea9-c2a7b16baa76', 'Szymon', 'Brzoza', '887f4ba5-9936-4ef6-9969-5974ee1a79f8'),
       ('9131b48f-5c47-4157-8cd2-a6883abf2024', 'Bartosz', 'Lot', '2f5b8fa5-45ff-461e-9ef1-182414052a21'),
       ('51b730c9-b0d9-42ed-b815-8e3339367344', 'Kuba', 'Zima', '32ef3937-3399-4a51-97e0-3045f180f3d2');
