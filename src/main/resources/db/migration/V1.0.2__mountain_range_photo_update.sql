alter table trip.mountain_range
    add photo_id int;

alter table trip.mountain_range
    add constraint mountain_range_photo_id_fk
        foreign key (photo_id) references trip.photo;