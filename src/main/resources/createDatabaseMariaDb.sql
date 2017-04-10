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