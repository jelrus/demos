drop table if exists users_roles;
drop table if exists roles;
drop table if exists users;

create table if not exists users(
    id BIGINT not null auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

create table if not exists roles(
    id BIGINT not null auto_increment primary key,
    name varchar(255) not null
);

create table if not exists users_roles(
    id BIGINT not null auto_increment primary key,
    user_id BIGINT not null,
    role_id BIGINT not null,
    UNIQUE KEY `unique_id`(`user_id`, `role_id`),
    foreign key (user_id) references users (id) ON DELETE CASCADE,
    foreign key (role_id) references roles (id) ON DELETE CASCADE
);