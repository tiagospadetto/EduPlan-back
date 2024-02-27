CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   email VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL,
   enabled BOOLEAN NOT NULL,
   email_verified BOOLEAN NOT NULL,
   role VARCHAR(100) NOT NULL,
   UNIQUE (email)
);