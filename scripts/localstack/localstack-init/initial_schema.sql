CREATE TABLE lottery_participant (
   id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255),
   email VARCHAR(255)
);

INSERT INTO lottery_participant (id, name, email) VALUES (1, 'Alice', 'alice@example.com');
INSERT INTO lottery_participant (id, name, email) VALUES (2, 'Bob', 'bob@example.com');

CREATE TABLE ballot (
   id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
   lottery_participant_id BIGINT(20) NOT NULL,
   ballot_number INT NOT NULL,
   date DATE NOT NULL,
   is_winner BOOLEAN NOT NULL DEFAULT FALSE,
   FOREIGN KEY (lottery_participant_id) REFERENCES lottery_participant(id),
   INDEX inx_date (date)
);
