DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id          BIGINT NOT NULL,
    email       VARCHAR(100),
    username    VARCHAR(50),
    password    VARCHAR(500),
    profile_url VARCHAR(500),
    create_at   TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (id)
);
