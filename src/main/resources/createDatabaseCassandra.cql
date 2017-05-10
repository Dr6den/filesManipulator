/**
 * Author:  andrew
 * Created: Apr 10, 2017
 */
DROP TABLE IF EXISTS file_statistics;
CREATE TABLE file_statistics (
    file_id UUID PRIMARY KEY,
    name text,
    path text,
    text_length INT,
    number_of_lines INT
);

DROP TABLE IF EXISTS text_line;
CREATE TABLE text_line (
    line_id UUID,
    file_id UUID,
    text_id INT,
    line text,
    PRIMARY KEY (line_id, file_id)
);