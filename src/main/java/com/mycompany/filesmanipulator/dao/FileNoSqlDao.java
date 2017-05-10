package com.mycompany.filesmanipulator.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.mycompany.filesmanipulator.dao.entity.nosql.FileStatisticsNoSqlEntity;
import com.mycompany.filesmanipulator.dao.entity.nosql.TextLineNoSqlEntity;
import com.mycompany.filesmanipulator.entity.TextFile;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author andrew
 */
public class FileNoSqlDao {
    public void save(TextFile textFile) {
        FileStatisticsNoSqlEntity stat = new FileStatisticsNoSqlEntity(textFile.getName(), textFile.getPath(),
                textFile.getTextLength(), textFile.getNumberOfLines(), textFile.getLines());
        List<TextLineNoSqlEntity> lineStat = textFile.getLines().stream()
                .map((String l) -> new TextLineNoSqlEntity(l, stat.getFileId())).collect(Collectors.toList());
        
        Mapper<FileStatisticsNoSqlEntity> mapper;
        Mapper<TextLineNoSqlEntity> innerLinesMapper;
        try (Session session = SessionManager.getCassandraSession()) {
            mapper = new MappingManager(session).mapper(FileStatisticsNoSqlEntity.class);
            innerLinesMapper = new MappingManager(session).mapper(TextLineNoSqlEntity.class);
            mapper.save(stat);
            
            lineStat.forEach((TextLineNoSqlEntity e) -> innerLinesMapper.save(e));
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
