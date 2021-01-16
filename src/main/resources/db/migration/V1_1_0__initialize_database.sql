drop table IF EXISTS poi;

create table poi
(
    name VARCHAR(256) PRIMARY KEY,
    x_coordinate INTEGER,
    y_coordinate INTEGER
);
