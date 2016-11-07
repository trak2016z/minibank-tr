create table app_user (
   id SERIAL NOT NULL PRIMARY KEY,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   city character varying(36) NOT NULL,
   postalcode character varying(36) NOT NULL,
   phone character varying(20) NOT NULL,
   image_token_id integer NOT NULL DEFAULT 1,
   last_login timestamp with time zone,
   error_login timestamp with time zone,
   UNIQUE (sso_id)
);

create table user_profile(
   id SERIAL NOT NULL PRIMARY KEY,
   type VARCHAR(30) NOT NULL,
   UNIQUE (type)
);

CREATE TABLE app_user_user_profile (
    user_id INTEGER NOT NULL,
    user_profile_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, user_profile_id)
);

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

CREATE TABLE cards
(
    id SERIAL NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    active boolean NOT NULL DEFAULT false
);

CREATE TABLE helpdesk
(
    id SERIAL NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    datee timestamp with time zone NOT NULL DEFAULT now(),
    title text NOT NULL,
    question text NOT NULL
);

CREATE TABLE image_token
(
    id SERIAL NOT NULL PRIMARY KEY,
    name character varying(36) NOT NULL,
    path character varying(36)  NOT NULL
);

CREATE TABLE saved_transactions
(
    id SERIAL NOT NULL PRIMARY KEY,
    wallet_number character varying(26) NOT NULL,
    value double precision NOT NULL,
    confidential boolean NOT NULL DEFAULT true,
    title text  NOT NULL,
    name character varying(61) NOT NULL,
    address1 character varying(30),
    address2 character varying(30),
    user_id integer NOT NULL
);

CREATE TABLE status
(
    id SERIAL NOT NULL PRIMARY KEY,
    name character varying(34) NOT NULL,
    description text
);

CREATE TABLE tokens
(
    id SERIAL NOT NULL PRIMARY KEY,
    card_id integer NOT NULL,
    content character varying(6) NOT NULL,
    used boolean NOT NULL DEFAULT false
);

CREATE TABLE transactions
(
    id SERIAL NOT NULL PRIMARY KEY,
    source_wallet_id integer NOT NULL,
    wallet_number character varying(26) NOT NULL,
    order_date timestamp with time zone NOT NULL DEFAULT now(),
    confirm_date timestamp with time zone,
    value double precision NOT NULL,
    status_id integer NOT NULL,
    confirmed boolean NOT NULL DEFAULT false,
    token_id integer NOT NULL,
    title text NOT NULL,
    name character varying(61) NOT NULL,
    address1 character varying(30),
    address2 character varying(30)
);

CREATE TABLE wallets
(
    id SERIAL NOT NULL PRIMARY KEY,
    user_id integer NOT NULL,
    "number" character varying(26) NOT NULL,
    content double precision NOT NULL DEFAULT 0.0
);


INSERT INTO APP_USER
VALUES (1, 'krzysztof.libront', '$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Krzysztof', 'Libront',
        'krzysztof.libront@gmail.com', 'Klęczany 148', '38-333 Zagórzany', '123-456-789', 1, null, null);

INSERT INTO USER_PROFILE(type)
VALUES ('USER');

INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE(type)
VALUES ('DBA');

INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id) VALUES (1, 2);

ALTER TABLE wallets ADD
    CONSTRAINT wallets_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES APP_USER (id);
ALTER TABLE transactions ADD
    CONSTRAINT transactions_status_id_fkey FOREIGN KEY (status_id)
        REFERENCES status (id);
ALTER TABLE transactions ADD
    CONSTRAINT transactions_token_id_fkey FOREIGN KEY (token_id)
        REFERENCES tokens (id);
ALTER TABLE tokens ADD
    CONSTRAINT tokens_card_id_fkey FOREIGN KEY (card_id)
        REFERENCES cards (id);
ALTER TABLE saved_transactions ADD
    CONSTRAINT saved_transactions_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES APP_USER (id);
ALTER TABLE helpdesk ADD
    CONSTRAINT helpdesk_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES APP_USER (id);
ALTER TABLE cards ADD
    CONSTRAINT cards_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES APP_USER (id);
ALTER TABLE app_user_user_profile ADD
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id);
ALTER TABLE app_user_user_profile ADD
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id);
ALTER TABLE APP_USER ADD
   CONSTRAINT users_image_token_id_fkey FOREIGN KEY (image_token_id)
        REFERENCES image_token (id);




jdbc.driverClassName = com.mysql.jdbc.Driver
jdbc.url = jdbc:postgresql://localhost:5432/bank
jdbc.username = postgres
jdbc.password = root
hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
hibernate.show_sql = true
hibernate.format_sql = true

jdbc.driverClassName = com.mysql.jdbc.Driver
jdbc.url = jdbc:postgresql://ec2-54-243-190-100.compute-1.amazonaws.com:5432/d1i5l5hr1b4ooi
jdbc.username = uxprvkeqasqoqz
jdbc.password = yNyTsxqiFmXMp9TUmR6yplrjC4
hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
hibernate.show_sql = true
hibernate.format_sql = true