--liquibase formatted sql

--changeset hasan:1.0.2
INSERT INTO `clinic`.`users` (`department_id`, `first_name`, `last_name`, `password`, `phone`, `status`, `username`) VALUES ('17', 'John', 'Doe', '$2a$12$ObSJ8CMHx.h6RTinEJJwmOEcUTUXyWdoj3c4olKIADu7JnJGjq1WK', '+998970000000', 'ACTIVE', 'admin');

INSERT INTO `clinic`.`user_roles` (`user_id`, `role_id`) VALUES ('1', '2');
