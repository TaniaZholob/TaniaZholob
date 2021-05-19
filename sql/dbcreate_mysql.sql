CONNECT
'jdbc:mysql://localhost:8080/projectdb;create=true;user=root;password=root';

DROP TABLE roles;
DROP TABLE users;
DROP TABLE orders;
DROP TABLE status;
DROP TABLE procedures;
DROP TABLE masters;
DROP TABLE time_slotes;

CREATE TABLE roles
(
    -- id has the INTEGER type (other name is INT), it is the primary key
    id   INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    -- role has the VARCHAR type - a string with a variable length
    -- roles values should not be repeated (UNIQUE)
    role VARCHAR(10) NOT NULL UNIQUE
);
SET
sql_mode='NO_AUTO_VALUE_ON_ZERO';
-- this three commands insert data into roles table
----------------------------------------------------------------

INSERT INTO roles
VALUES (DEFAULT, 'admin');
INSERT INTO roles
VALUES (NULL, 'client');
INSERT INTO roles
VALUES (null, 'master');
----------------------------------------------------------------
-- USERS
----------------------------------------------------------------
CREATE TABLE users
(
-- 'generated always AS identity' means id is autoincrement field
-- (from 1 up to Integer.MAX_VALUE with the step 1)
    id          INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,

-- 'UNIQUE' means logins values should not be repeated in login column of table
    login       VARCHAR(20) NOT NULL UNIQUE,

-- not null string columns
    password    VARCHAR(10) NOT NULL,
    firstName  VARCHAR(20) NOT NULL,
    lastName   VARCHAR(20) NOT NULL,
    localeName VARCHAR(20),

-- this declaration contains the foreign key constraint
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
    role_id     INTEGER     NOT NULL,
    FOREIGN KEY (role_id)
        REFERENCES roles (id)

-- removing a row with some ID from roles table implies removing
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in
-- users table with ROLES_ID=ID
        ON DELETE CASCADE

-- the same as previous but updating is used insted deleting
        ON UPDATE RESTRICT
);

-- id = 1
INSERT INTO users
VALUES (null, 'admin', 'admin', 'Ivan', 'Ivanov', NULL, 0);
-- id = 2
INSERT INTO users
VALUES (null, 'client', 'client', 'Petr', 'Petrov', NULL, 1);
-- id = 3
INSERT INTO users
VALUES (null, 'master', 'master', 'Tania', 'Zholob', NULL, 2);
----------------------------------------------------------------
-- PERFORMANCE_STATUSES
-- statuses for orders work performance
----------------------------------------------------------------
CREATE TABLE performance_statuses
(
    id   INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO performance_statuses
VALUES (DEFAULT, 'active');
INSERT INTO performance_statuses
VALUES (null, 'canceled');
INSERT INTO performance_statuses
VALUES (null, 'closed');

----------------------------------------------------------------
-- PAYMENT_STATUSES
-- statuses for orders work performance
----------------------------------------------------------------
CREATE TABLE payment_statuses
(
    id   INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO payment_statuses
VALUES (DEFAULT , 'opened');
INSERT INTO payment_statuses
VALUES (NULL , 'confirmed');
INSERT INTO payment_statuses
VALUES (NULL , 'paid');
INSERT INTO payment_statuses
VALUES (NULL , 'closed');
----------------------------------------------------------------
-- MASTERS
----------------------------------------------------------------
CREATE TABLE masters
(
    id      INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(15) NOT NULL,
    surname VARCHAR(15) NOT NULL,
    rating  INTEGER check (rating >= 0 and rating <= 10)
);

insert into masters
values (null, 'Morris', 'Lewis', 0);
insert into masters
values (null, 'Chase', 'Samuel', 0);
insert into masters
values (null, 'Ellery', 'William', 0);
insert into masters
values (null, 'Gerry', 'Elbridge', 0);
insert into masters
values (null, 'Hall', 'Lyman', 0);
insert into masters
values (null, 'Hart', 'John', 0);
insert into masters
values (null, 'Jefferson', 'Thomas', 0);
insert into masters
values (null, 'Lewis', 'Francis', 0);

----------------------------------------------------------------
-- CATEGORIES_PROCEDURES
----------------------------------------------------------------
CREATE TABLE categories_procedures
(
    id    INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30) NOT NULL
);
-- this six commands insert data into categories_procedures table
----------------------------------------------------------------
insert into categories_procedures
values (DEFAULT, 'haircuts, styling');
insert into categories_procedures
values (null, 'coloring');
insert into categories_procedures
values (null, 'hair care');
insert into categories_procedures
values (null, 'manicure, pedicure');
insert into categories_procedures
values (null, 'cosmetology, massage');
insert into categories_procedures
values (null, 'make-up, eyebrow design');
----------------------------------------------------------------
-- PROCEDURES
----------------------------------------------------------------
CREATE TABLE procedures
(
    id                    INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title                 VARCHAR(20) NOT NULL,
    price                 INTEGER     NOT NULL check (price > 0),
    category_procedure_id INTEGER     NOT NULL,
    FOREIGN KEY (category_procedure_id)
        REFERENCES categories_procedures (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);
insert into procedures
values (null, 'haircut', 700, 3);
insert into procedures
values (null, 'styling', 350, 3);
insert into procedures
values (null, 'weaving', 400, 3);
insert into procedures
values (null, 'hairstyle', 800, 3);
----------------------------------------------------------------
-- PROCEDURES_MASTERS
----------------------------------------------------------------
CREATE TABLE procedures_masters
(
    procedure_id INTEGER NOT NULL,
    master_id    INTEGER NOT NULL,
    UNIQUE (procedure_id, master_id),
    FOREIGN KEY (procedure_id)
        REFERENCES categories_procedures (id)
        ON DELETE CASCADE,
    FOREIGN KEY (master_id)
        REFERENCES masters (id)
        ON DELETE CASCADE
);
----------------------------------------------------------------
-- TIME_SLOTS
----------------------------------------------------------------
CREATE TABLE time_slots
(
    id        INTEGER  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date_time DATETIME NOT NULL,
    master_id INTEGER  NOT NULL,
    UNIQUE (date_time, master_id)
);
----------------------------------------------------------------
-- ORDERS
----------------------------------------------------------------
CREATE TABLE orders
(
    id                      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bill                    INTEGER NOT NULL DEFAULT 0,
    user_id                 INTEGER NOT NULL,
    procedure_id            INTEGER NOT NULL,
    payment_status_id       INTEGER NOT NULL,
    performance_statuses_id INTEGER NOT NULL,
    time_slot_id            INTEGER NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (procedure_id)
        REFERENCES categories_procedures (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (payment_status_id)
        REFERENCES payment_statuses (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (performance_statuses_id)
        REFERENCES performance_statuses (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FOREIGN KEY (time_slot_id)
        REFERENCES time_slots (id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
);
----------------------------------------------------------------
-- REVIEWS
----------------------------------------------------------------
create table reviews
(
    id        INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    master_id INTEGER     NOT NULL,
    user_id   INTEGER     NOT NULL,
    review    VARCHAR(60) NOT NULL,
    rating    INTEGER CHECK (rating >= 0 AND rating <= 10),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (master_id)
        REFERENCES masters (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);