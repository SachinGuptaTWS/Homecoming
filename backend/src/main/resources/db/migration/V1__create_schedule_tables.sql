CREATE TABLE events (
    id              VARCHAR(64)  PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    schedule_label  VARCHAR(128) NOT NULL,
    location        VARCHAR(255) NOT NULL
);

CREATE TABLE home_highlights (
    singleton_id    SMALLINT     PRIMARY KEY CHECK (singleton_id = 1),
    title           VARCHAR(255) NOT NULL,
    dates           VARCHAR(128) NOT NULL
);
