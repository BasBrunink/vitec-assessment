DROP TABLE IF EXISTS users;
CREATE TABLE users(id SERIAL PRIMARY KEY, name VARCHAR(50), email VARCHAR(50), password VARCHAR(50));

INSERT INTO users (name, email) VALUES ('Bas', 'bas@example.com');
INSERT INTO users (name, email) VALUES ('Maaike', 'maaike@example.com');
INSERT INTO users (name, email) VALUES ('Roy', 'roy@example.com');
INSERT INTO users (name, email) VALUES ('Bennie', 'bennie@example.com');
INSERT INTO users (name, email) VALUES ('Hetty', 'hetty@example.com');
INSERT INTO users (name, email) VALUES ('Gwen', 'gwen@example.com');
INSERT INTO users (name, email) VALUES ('Emily', 'emily@example.com');
INSERT INTO users (name, email) VALUES ('Ruud', 'ruud@example.com');
INSERT INTO users (name, email) VALUES ('Rini', 'rini@example.com');
INSERT INTO users (name, email) VALUES ('Stef', 'stef@example.com');
INSERT INTO users (name, email) VALUES ('Suus', 'suus@example.com');