INSERT INTO public.region (id, description, name) VALUES (nextval('region_id_seq'), 'Konin długi opis. Konin długi opis. Konin długi opis.', 'Konin');

INSERT INTO public.infrastructure_type (name) VALUES ('Pies');
INSERT INTO public.infrastructure_type (name) VALUES ('Owca');
INSERT INTO public.infrastructure_type (name) VALUES ('Pomnik');

INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Złota góra długi opis. Złota góra długi opis. Złota góra długi opis.', 'Złota góra', 2);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Ta skarpa z piasku co teraz zarosła. Tam co wędkarze łowią ryby.', 'Skarpa nad Wartą', 2);

with row as (
    INSERT INTO public.attraction (id, name, description, region_id) VALUES (nextval('attraction_id_seq'), 'Rysiu', 'Cudowny Rysiczek', 2) RETURNING id
)
INSERT INTO public.attraction_infrastructure (attraction_id, infrastructure_id) SELECT id, 'Pies' FROM row;

with row as (
    INSERT INTO public.attraction (id, name, description, region_id) VALUES (nextval('attraction_id_seq'), 'Lusia', 'Piękna Lusica', 2) RETURNING id
)
INSERT INTO public.attraction_infrastructure (attraction_id, infrastructure_id) SELECT id, 'Pies' FROM row;

with row as (
    INSERT INTO public.attraction (id, name, description, region_id) VALUES (nextval('attraction_id_seq'), 'Piesek Podhalański', 'Kudłaty', 1) RETURNING id
)
INSERT INTO public.attraction_infrastructure (attraction_id, infrastructure_id) SELECT id, 'Pies' FROM row;

with row as (
    INSERT INTO public.attraction (id, name, description, region_id) VALUES (nextval('attraction_id_seq'), 'Biała owca', 'Standardowa biała owieczka', 1) RETURNING id
)
INSERT INTO public.attraction_infrastructure (attraction_id, infrastructure_id) SELECT id, 'Owca' FROM row;

with row as (
    INSERT INTO public.attraction (id, name, description, region_id) VALUES (nextval('attraction_id_seq'), 'Czarna owca', 'Mniej standardowa, ale ciągle bardzo mięciutka owieczka', 1) RETURNING id
)
INSERT INTO public.attraction_infrastructure (attraction_id, infrastructure_id) SELECT id, 'Owca' FROM row;

with row as (
    INSERT INTO public.attraction (id, name, description, region_id) VALUES (nextval('attraction_id_seq'), 'Kamień milowy', 'Jedyny zabytek w Koninie', 2) RETURNING id
)
INSERT INTO public.attraction_infrastructure (attraction_id, infrastructure_id) SELECT id, 'Pomnik' FROM row;