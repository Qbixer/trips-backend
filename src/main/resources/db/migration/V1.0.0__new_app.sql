create schema trip;

create table trip.region
(
    id serial not null
        constraint region_pk
            primary key,
    name varchar(255) not null,
    description text
);

create unique index region_name_uindex
    on trip.region (name);

create table trip.attraction
(
    id serial not null
        constraint attraction_pk
            primary key,
    name varchar(255) not null,
    description text,
    region_id int
        constraint attraction_region_id_fk
            references trip.region
);

create unique index attraction_name_uindex
    on trip.attraction (name);

create table trip.mountain_range
(
    id serial not null
        constraint mountain_range_pk
            primary key,
    name varchar(255) not null,
    description text,
    region_id int
        constraint mountain_range_region_id_fk
            references trip.region
);

create unique index mountain_range_name_uindex
    on trip.mountain_range (name);

create table trip.peak
(
    id serial not null
        constraint peak_pk
            primary key,
    name varchar(255) not null,
    description text,
    height int,
    mountain_range_id int
        constraint peak_mountain_range_id_fk
            references trip.mountain_range
);

create unique index peak_name_uindex
    on trip.peak (name);

create table trip.trip
(
    id serial not null
        constraint trip_pk
            primary key,
    name varchar(255) not null,
    description text,
    mapa_turystyczna_link text,
    date timestamp,
    region_id int
        constraint trip_region_id_fk
            references trip.region
);

create unique index trip_name_uindex
    on trip.trip (name);

create table trip.trip_mountain_range
(
    trip_id int not null,
    mountain_range_id int not null,
    constraint trip_mountain_range_pk
        primary key (trip_id, mountain_range_id)
);

create table trip.trip_peak
(
    trip_id int not null,
    peak_id int not null,
    constraint trip_peak_pk
        primary key (trip_id, peak_id)
);

create table trip.photo
(
    id serial not null
        constraint photo_pk
            primary key,
    name varchar(255),
    photo bytea not null
);

create table trip.trip_photo
(
    trip_id int not null,
    photo_id int not null,
    constraint trip_photo_pk
        primary key (trip_id, photo_id)
);
