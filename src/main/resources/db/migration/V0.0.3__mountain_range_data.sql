INSERT INTO public.region (id, description, name) VALUES (nextval('region_id_seq'), 'Podhale długi opis. Podhale długi opis. Podhale długi opis.', 'Podhale');

INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Tatry długi opis. Tatry długi opis. Tatry długi opis.', 'Tatry', 1);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Pieniny długi opis. Pieniny długi opis. Pieniny długi opis.', 'Pieniny', 1);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Beskid Żywiecki długi opis. Beskid Żywiecki długi opis. Beskid Żywiecki długi opis.', 'Beskid Żywiecki', 1);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Gorce długi opis. Gorce długi opis. Gorce długi opis.', 'Gorce', 1);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Beskid Wyspowy długi opis. Beskid Wyspowy długi opis. Beskid Wyspowy długi opis.', 'Beskid Wyspowy', 1);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Beskid Makowski długi opis. Beskid Makowski długi opis. Beskid Makowski długi opis.', 'Beski Makowski', 1);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Beskid Sądecki długi opis. Beskid Sądecki długi opis. Beskid Sądecki długi opis.', 'Beskid Sądecki', 1);
INSERT INTO public.mountain_range (id, description, name, region_id) VALUES (nextval('mountain_range_id_seq'), 'Beski Niski długi opis. Beski Niski długi opis. Beski Niski długi opis.', 'Beski Niski', 1);

INSERT INTO public.peak (id, name, description, height, stamp, mountain_range_id) VALUES (nextval('peak_id_seq'), 'Rysy', 'Rysy długi opis. Rysy długi opis. Rysy długi opis.', 2499, false, 1);
INSERT INTO public.peak (id, name, description, height, stamp, mountain_range_id) VALUES (nextval('peak_id_seq'), 'Kościelec', 'Kościelec długi opis. Kościelec długi opis. Kościelec długi opis.', 2155, false, 1);