CREATE TABLE lottery_participant (
   id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255),
   email VARCHAR(255)
);

INSERT INTO lottery_participant (id, name, email) VALUES (1, 'Alice', 'alice@example.com');
INSERT INTO lottery_participant (id, name, email) VALUES (2, 'Bob', 'bob@example.com');


