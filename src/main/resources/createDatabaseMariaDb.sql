/**
 * Author:  andrew
 * Created: Apr 10, 2017
 */
DROP TABLE IF EXISTS file_statistics;
CREATE TABLE file_statistics (
    file_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    path VARCHAR(200),
    text_length INT,
    number_of_lines INT
);

DROP TABLE IF EXISTS text_line;
CREATE TABLE text_line (
    line_id INT AUTO_INCREMENT PRIMARY KEY,
    text_id INT,
    line VARCHAR(200),
    FOREIGN KEY (text_id) REFERENCES file_statistics(file_id)
);

DROP TABLE IF EXISTS flat_owner;
CREATE TABLE flat_owner(
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(30),
    documents VARCHAR(30)
);

DROP TABLE IF EXISTS flat;
CREATE TABLE flat(
    flat_id INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(30)
);

DROP TABLE IF EXISTS registration;
CREATE TABLE registration(
    id INT NOT NULL,
    owner_id INT DEFAULT NULL,
    flat_id INT DEFAULT NULL
);

ALTER TABLE registration ADD INDEX(owner_id);
ALTER TABLE registration ADD INDEX(flat_id);

ALTER TABLE registration
ADD CONSTRAINT st_owner_id
FOREIGN KEY (owner_id) REFERENCES flat_owner(id)
ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE registration
ADD CONSTRAINT st_flat_id
FOREIGN KEY (flat_id) REFERENCES flat(flat_id)
ON UPDATE CASCADE ON DELETE CASCADE;