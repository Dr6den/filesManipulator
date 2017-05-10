package com.mycompany.filesmanipulator.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.mycompany.filesmanipulator.dao.entity.nosql.FileStatisticsNoSqlEntity;
import com.mycompany.filesmanipulator.entity.TextFile;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author andrew
 */
public class FileNoSqlDao {
    public void save(TextFile textFile) {
        FileStatisticsNoSqlEntity stat = new FileStatisticsNoSqlEntity(textFile.getName(), textFile.getPath(),
                textFile.getTextLength(), textFile.getNumberOfLines(), textFile.getLines());
        Mapper<FileStatisticsNoSqlEntity> mapper;
        try (Session session = SessionManager.getCassandraSession()) {
            mapper = new MappingManager(session).mapper(FileStatisticsNoSqlEntity.class);
            mapper.save(stat);
        }
    }
    
    public FileStatisticsNoSqlEntity getTextFileByName(String name) {
        FileStatisticsNoSqlEntity textFile = null;
        try (Session session = SessionManager.getCassandraSession()) {
            MappingManager manager = new MappingManager(session);
            FileNoSqlAccestor fileAccessor = manager.createAccessor(FileNoSqlAccestor.class);
            ResultSet rs = fileAccessor.selectByName(name);           
            Row row = rs.one();
            if (row != null) {
                textFile = new FileStatisticsNoSqlEntity();
                textFile.setFileId(row.getUUID("file_id"));
                textFile.setName(row.getString("name"));
                textFile.setPath(row.getString("path"));
                textFile.setNumberOfLines(row.getInt("number_of_lines"));
                textFile.setTextLength(row.getInt("text_length"));
            }
        }
        return textFile;
    }
    
    public void deleteTextFile(FileStatisticsNoSqlEntity textFile) {
        Mapper<FileStatisticsNoSqlEntity> mapper;
        try (Session session = SessionManager.getCassandraSession()) {
            mapper = new MappingManager(session).mapper(FileStatisticsNoSqlEntity.class);
            mapper.delete(textFile);
        }
    }
}
