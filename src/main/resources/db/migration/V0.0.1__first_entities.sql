create table region
(
    id serial not null
        constraint region_pk
            primary key,
    description varchar(2000),
    name varchar(255)
);

create table route
(
    id serial not null
        constraint route_pkey
            primary key,
    description varchar(2000),
    name varchar(255)
);

create table infrastructure_type
(
    name varchar(255) not null
        constraint infrastructure_type_pk
            primary key
);

create table mountain_range
(
    id serial not null
        constraint mountain_range_pk
            primary key,
    description varchar(2000),
    name varchar(255),
    region_id int
        constraint mountain_range_region_id_fk
            references region
);

create table peak
(
    id serial not null
        constraint peak_pk
            primary key,
    height float,
    description varchar(2000),
    name varchar(255),
    stamp boolean,
    mountain_range_id int
        constraint peak_mountain_range_id_fk
            references mountain_range
);

create table peak_route
(
    route_id int not null
        constraint peak_route_route_id_fk
            references route,
    peak_id int not null
        constraint peak_route_peak_id_fk
            references peak,
    constraint peak_route_pk
        unique (route_id, peak_id)
);

create table point
(
    id serial not null
        constraint point_pk
            primary key,
    description varchar(2000),
    name varchar(255)
);

create table point_color
(
    point_id int not null
        constraint point_color_point_id_fk
            references point,
    color varchar(255) not null,
    constraint point_color_pk
        primary key (point_id, color)
);

create table point_infrastructure
(
    point_id int not null
        constraint point_infrastructure_point_id_fk
            references point,
    infrastructure_id varchar(255) not null
        constraint point_infrastructure_infrastructure_type_name_fk
            references infrastructure_type,
    constraint point_infrastructure_pk
        primary key (point_id, infrastructure_id)
);

create table point_point_type
(
    point_id int not null
        constraint point_point_type_point_id_fk
            references point,
    point_type varchar(255) not null,
    constraint point_point_type_pk
        primary key (point_id, point_type)
);

create table route_point
(
    route_id int not null
        constraint route_point_route_id_fk
            references route,
    point_id int not null
        constraint route_point_point_id_fk
            references point,
    constraint route_point_pk
        unique (route_id, point_id)
);

create table attraction
(
    id serial not null
        constraint attraction_pk
            primary key,
    name varchar(255),
    description varchar(2000),
    region_id int
        constraint attraction_region_id_fk
            references region
);

create table attraction_infrastructure
(
    attraction_id int not null
        constraint attraction_infrastructure_attraction_id_fk
            references attraction,
    infrastructure_id varchar(255) not null
        constraint attraction_infrastructure_infrastructure_type_name_fk
            references infrastructure_type,
    constraint attraction_infrastructure_pk
        unique (attraction_id, infrastructure_id)
);
