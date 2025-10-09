-- liquibase formatted sql

-- changeset heeseo:1759859311751-1
CREATE TABLE food
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_food PRIMARY KEY (id)
);

-- changeset heeseo:1759859311751-2
CREATE TABLE member
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    name       VARCHAR(3)   NOT NULL,
    gender     VARCHAR(255) NOT NULL,
    birth      date         NOT NULL,
    address    VARCHAR(255) NULL,
    phone      VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    point      INT NULL,
    deleted_at datetime NULL,
    CONSTRAINT pk_member PRIMARY KEY (id)
);

-- changeset heeseo:1759859311751-3
CREATE TABLE member_food
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    member_id BIGINT NULL,
    food_id   BIGINT NULL,
    CONSTRAINT pk_member_food PRIMARY KEY (id)
);

-- changeset heeseo:1759859311751-4
CREATE TABLE member_term
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    term_id   BIGINT NULL,
    member_id BIGINT NULL,
    CONSTRAINT pk_member_term PRIMARY KEY (id)
);

-- changeset heeseo:1759859311751-5
CREATE TABLE reply
(
    id BIGINT AUTO_INCREMENT NOT NULL,
    CONSTRAINT pk_reply PRIMARY KEY (id)
);

-- changeset heeseo:1759859311751-6
CREATE TABLE review
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    reply_id BIGINT NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

-- changeset heeseo:1759859311751-7
CREATE TABLE term
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_term PRIMARY KEY (id)
);

-- changeset heeseo:1759859311751-8
ALTER TABLE review
    ADD CONSTRAINT uc_review_reply UNIQUE (reply_id);

-- changeset heeseo:1759859311751-9
ALTER TABLE member_food
    ADD CONSTRAINT FK_MEMBER_FOOD_ON_FOOD FOREIGN KEY (food_id) REFERENCES food (id);

-- changeset heeseo:1759859311751-10
ALTER TABLE member_food
    ADD CONSTRAINT FK_MEMBER_FOOD_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (id);

-- changeset heeseo:1759859311751-11
ALTER TABLE member_term
    ADD CONSTRAINT FK_MEMBER_TERM_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (id);

-- changeset heeseo:1759859311751-12
ALTER TABLE member_term
    ADD CONSTRAINT FK_MEMBER_TERM_ON_TERM FOREIGN KEY (term_id) REFERENCES term (id);

-- changeset heeseo:1759859311751-13
ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_REPLY FOREIGN KEY (reply_id) REFERENCES reply (id);

