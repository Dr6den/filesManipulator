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
    number_of_lines INT,
    lines list<text>
);