
    CREATE TABLE IF NOT EXISTS worker (
        ID SERIAL PRIMARY KEY,
        NAME VARCHAR(1000) CHECK (LENGTH(name) > 1) NOT NULL,
        BIRTHDAY DATE CHECK (BIRTHDAY > '1900-12-31'),
        LEVEL VARCHAR(8) CHECK (LEVEL IN ('Trainee', 'Junior', 'Middle', 'Senior')) NOT NULL ,
        SALARY INT CHECK (SALARY <= 100000 AND SALARY >= 100)
    );

    CREATE TABLE IF NOT EXISTS client (
        ID SERIAL PRIMARY KEY,
        NAME VARCHAR(1000) CHECK (LENGTH(NAME) > 1) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS project (
        ID SERIAL PRIMARY KEY,
        CLIENT_ID BIGINT NOT NULL,
        START_DATE DATE,
        FINISH_DATE DATE,
        FOREIGN KEY (CLIENT_ID) REFERENCES client (ID)
    );

    CREATE TABLE IF NOT EXISTS project_worker (
        PROJECT_ID BIGINT NOT NULL,
        WORKER_ID BIGINT NOT NULL,
        PRIMARY KEY (PROJECT_ID, WORKER_ID),
        FOREIGN KEY (PROJECT_ID) REFERENCES project (ID),
        FOREIGN KEY (WORKER_ID) REFERENCES worker (ID)
    );