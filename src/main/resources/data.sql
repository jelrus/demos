INSERT INTO roles
values (default, 'ROLE_ADMIN');

INSERT INTO roles
values (default, 'ROLE_USER');

INSERT INTO users
values (default, 'user', '$2a$12$mgNxpzkeN04JWXhsGDIrA.wPlW3oO0PPtrRV.bg.uWbfFPH6B.3am');

INSERT INTO users
values (default, 'admin', '$2a$12$mgNxpzkeN04JWXhsGDIrA.wPlW3oO0PPtrRV.bg.uWbfFPH6B.3am');

INSERT INTO users_roles
values (default, 1, 2);

INSERT INTO users_roles
values (default, 2, 1);
