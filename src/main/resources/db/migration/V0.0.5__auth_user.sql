create table my_user
(
    id serial not null
        constraint user_pk
            primary key,
    email varchar(255),
    password varchar(1024)
);

create table user_role
(
    user_id int not null
        constraint user_role_user_id_fk
            references my_user,
    role varchar(255) not null,
    constraint user_role_pk
        primary key (user_id, role)
);