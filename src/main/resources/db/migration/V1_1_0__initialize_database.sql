drop table IF EXISTS poi;

create table poi
(
    name VARCHAR(256) PRIMARY KEY,
    x_coordinates INTEGER,
    y_coordinates INTEGER
);
