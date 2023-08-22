;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS USER_ROLES;
DROP TABLE IF EXISTS CHOICE_RESTAURANT;
DROP TABLE IF EXISTS RESTAURANT;
DROP TABLE IF EXISTS LUNCH;

CREATE TABLE CHOICE_RESTAURANT (
  ID            BIGINT                  NOT NULL,
  CHOICE_DATE   TIMESTAMP DEFAULT NOW() NOT NULL,
  RESTAURANT_ID BIGINT                  NOT NULL,
  USER_ID       BIGINT                  NOT NULL
);
ALTER TABLE CHOICE_RESTAURANT ADD CONSTRAINT CHOICE_RESTAURANT_ID PRIMARY KEY (ID);
ALTER TABLE CHOICE_RESTAURANT ADD CONSTRAINT UNIQUE_USER_CHOICE_DATE_IDX UNIQUE(USER_ID, CHOICE_DATE);

CREATE TABLE LUNCH (
  ID            BIGINT NOT NULL,
  LUNCH_DATE    DATE  NOT NULL,
  NAME          VARCHAR(100),
  PRICE         INTEGER NOT NULL,
  RESTAURANT_ID BIGINT NOT NULL
);
ALTER TABLE LUNCH ADD CONSTRAINT LUNCH_ID PRIMARY KEY (ID);
ALTER TABLE LUNCH ADD CONSTRAINT UNIQUE_RESTAURANT_ID_NAME_IDX UNIQUE(RESTAURANT_ID, NAME);

CREATE TABLE RESTAURANT (
  ID   BIGINT NOT NULL,
  NAME VARCHAR(100)
);
ALTER TABLE RESTAURANT ADD CONSTRAINT RESTAURANT_ID PRIMARY KEY (ID);
ALTER TABLE RESTAURANT ADD CONSTRAINT UNIQUE_RESTAURANT_NAME_IDX UNIQUE(NAME);


CREATE TABLE USER_ROLES (
  USER_ID BIGINT NOT NULL,
  ROLE    VARCHAR(255)
);

CREATE TABLE USERS (
  ID         BIGINT                  NOT NULL,
  NAME       VARCHAR(100),
  EMAIL      VARCHAR(100)            NOT NULL,
  ENABLED    BOOL DEFAULT TRUE       NOT NULL,
  PASSWORD   VARCHAR(100)            NOT NULL,
  REGISTERED TIMESTAMP DEFAULT NOW() NOT NULL
);
ALTER TABLE USERS ADD CONSTRAINT USERS_ID PRIMARY KEY (ID);
ALTER TABLE USERS ADD CONSTRAINT UNIQUE_USERS_EMAIL_IDX UNIQUE(EMAIL);

ALTER TABLE LUNCH ADD CONSTRAINT FK_LUNCH_RESTAURANT_IDX FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANT(ID) NOCHECK;
ALTER TABLE USER_ROLES ADD CONSTRAINT FK_USER_ROLES_USERS_IDX FOREIGN KEY(USER_ID) REFERENCES USERS(ID) NOCHECK;
ALTER TABLE CHOICE_RESTAURANT ADD CONSTRAINT FK_CHOICE_RESTAURANT_USERS_IDX FOREIGN KEY(USER_ID) REFERENCES USERS(ID) NOCHECK;
ALTER TABLE CHOICE_RESTAURANT ADD CONSTRAINT FK_CHOICE_RESTAURANT_IDX FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANT(ID) NOCHECK;
