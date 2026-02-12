--liquibase formatted sql

--changeset pulsar:1
CREATE TABLE users
(
    id           UUID PRIMARY KEY,
    username     VARCHAR(128) UNIQUE,
    phone_number VARCHAR(32)  NOT NULL UNIQUE,
    display_name VARCHAR(128) NOT NULL,
    bio          VARCHAR(256),
    birthdate    DATE,
    created_at   TIMESTAMP    NOT NULL
);

CREATE UNIQUE INDEX idx_users_username ON users (username) WHERE username IS NOT NULL;
CREATE INDEX idx_users_phone_number ON users (phone_number);

--changeset pulsar:2
CREATE TABLE sessions
(
    id         UUID PRIMARY KEY,
    token      VARCHAR(64) NOT NULL UNIQUE,
    user_id    UUID REFERENCES users (id) ON DELETE CASCADE,
    created_at TIMESTAMP   NOT NULL,
    expired_at TIMESTAMP   NOT NULL,
    ip_address VARCHAR(64),
    user_agent VARCHAR(128)
);

CREATE INDEX idx_sessions_user_id ON sessions (user_id);
