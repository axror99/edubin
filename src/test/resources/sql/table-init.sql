CREATE SCHEMA IF NOT EXISTS edubin;

create table if not exists edubin.user
(
    id
    serial
    primary
    key,
    name
    varchar,
    price
    double
    precision,
    description
    varchar,
    image_url
    varchar
);


CREATE TABLE if not exists edubin.UserEntity
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    name        VARCHAR(255),
    birthDay    DATE,
    picture     VARCHAR(255),
    profession  VARCHAR(255),
    about       TEXT,
    achievement TEXT,
    myObjective TEXT,
    email       VARCHAR(255) NOT NULL UNIQUE,
    roles       TEXT[],
    permission  TEXT[]
);

CREATE TABLE if not exists edubin.CommentEntity
(
    id         INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    comment    TEXT,
    user_id    INTEGER,
    teacher_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES UserEntity (id),
    FOREIGN KEY (teacher_id) REFERENCES UserEntity (id)
);

CREATE TABLE if not exists edubin.SocialMediaEntity
(
    id        INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    twitter   VARCHAR(255),
    facebook  VARCHAR(255),
    linkedin  VARCHAR(255),
    instagram VARCHAR(255),
    user_id   INTEGER UNIQUE,
    FOREIGN KEY (user_id) REFERENCES UserEntity (id)
);