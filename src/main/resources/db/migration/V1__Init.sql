USE tb;

CREATE TABLE profiles
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT '',
  description VARCHAR(255) DEFAULT ''
);

CREATE TABLE timing_configs
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT '' NOT NULL,
  description VARCHAR(255) DEFAULT '',
  profile_id BIGINT NOT NULL,
  FOREIGN KEY profile_timing_config_fk (profile_id) REFERENCES profiles (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE timings
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT '' NOT NULL,
  unit VARCHAR(255) DEFAULT '',
  duration BIGINT DEFAULT 0,
  timing_config_id BIGINT NOT NULL,
  FOREIGN KEY timing_config_timing_fk (timing_config_id) REFERENCES timing_configs (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE time_mark
(
  id BIGINT AUTO_INCREMENT,
  timing_id BIGINT NOT NULL,
  mark DATETIME NOT NULL,
  description TEXT,
  stopMark BIT NOT NULL,

  PRIMARY KEY (id, timing_id, mark)
)