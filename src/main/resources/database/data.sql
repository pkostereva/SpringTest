INSERT INTO cities (id, name, parser_alias) VALUES
    (1, 'Moscow', 'moscow'),
    (2, 'Saint Petersburg', 'spb'),
    (3, 'Sochi', 'sochi');

INSERT INTO users (name, password, city_id) VALUES
    ('Bob', 'easy', 3),
    ('Tim', 'simple', 2),
    ('Alex', 'bad', 1);