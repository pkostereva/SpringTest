CREATE TABLE IF NOT EXISTS cities
(
    id           SMALLINT PRIMARY KEY ,
    name         VARCHAR(254) NOT NULL ,
    parser_alias VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY ,
    name     VARCHAR(254) NOT NULL ,
    password VARCHAR(254) NOT NULL ,
    city_id  SMALLINT NOT NULL REFERENCES Cities (Id)
);

CREATE UNIQUE INDEX users_username_uidx
  ON users (name);

CREATE TABLE IF NOT EXISTS requests_history
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT NOT NULL REFERENCES Users (Id),
    temperature VARCHAR(2) NOT NULL,
    created     TIMESTAMP DEFAULT now()
);