CREATE TABLE users(
username VARCHAR(50) PRIMARY KEY,
password VARCHAR(100) NOT NULL,
email VARCHAR(50) NOT NULL,
authority VARCHAR(50) NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT username CHECK (LENGTH(username)>=3 AND LENGTH(username)<=50);

ALTER TABLE users
    ADD CONSTRAINT password CHECK (LENGTH(password)>=6 AND LENGTH(password)<=100);

ALTER TABLE users
    ADD CONSTRAINT authority CHECK (LENGTH(authority)>=3 AND LENGTH(authority)<=50);

CREATE TABLE note(
id IDENTITY PRIMARY KEY,
username VARCHAR(50) NOT NULL,
title VARCHAR(50) NOT NULL,
content VARCHAR(300) NOT NULL,
FOREIGN KEY(username) REFERENCES users(username) ON DELETE CASCADE
);

ALTER TABLE note
    ADD CONSTRAINT title CHECK (LENGTH(title)>=3 AND LENGTH(title)<=50);

ALTER TABLE note
    ADD CONSTRAINT content CHECK (LENGTH(content)>=3 AND LENGTH(content)<=300);


