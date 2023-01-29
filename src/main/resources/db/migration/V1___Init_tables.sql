create table if not exists employments(
    id serial primary key,
    duration time not null ,
    name varchar(255) not null
);

create table if not exists users(
    id serial primary key,
    username varchar(255) not null unique ,
    password varchar(255) not null ,
    enabled boolean
);

create table if not exists roles(
    id serial primary key,
    name varchar(255) not null unique
);

create table if not exists users_roles(
    user_id int references users(id),
    roles_id int references roles(id)
);

create table if not exists tasks(
    id serial primary key,
    start_time timestamp not null ,
    finish_time timestamp not null ,
    status varchar(255) not null ,
    user_id int references users(id) not null ,
    employment_id int references employments(id) not null
);

insert into employments(name, duration) values ('Wash a car', '00:30:00'), ('Polish mirrors', '00:00:12');
insert into users (username, password, enabled) values ('user', '$2a$10$KBMBZLxY/I/l7Bjt.cGEveIjggYpqa34mGW/BjVd0zdMWyS7t7qlm', true),
                                                       ('admin', '$2a$10$ph4fBNejGkVJZSYkiLeB4uoKBePeO7U3KE0Ql6tXjkMPP4frAP622', true);
insert into roles(name) values ('ROLE_USER'), ('ROLE_ADMIN');
insert into users_roles (user_id, roles_id) values (1, 1), (2, 2);