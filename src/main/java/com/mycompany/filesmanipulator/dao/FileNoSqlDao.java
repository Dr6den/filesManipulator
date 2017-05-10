package com.mycompany.filesmanipulator.dao;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.mycompany.filesmanipulator.dao.entity.nosql.FileStatisticsNoSqlEntity;
import com.mycompany.filesmanipulator.entity.TextFile;

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
}
