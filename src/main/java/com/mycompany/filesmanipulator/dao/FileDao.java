package com.mycompany.filesmanipulator.dao;

import com.mycompany.filesmanipulator.dao.entity.FileStatistics;
import com.mycompany.filesmanipulator.entity.TextFile;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author andrew
 */
public class FileDao {
    public void save(TextFile textFile) {
        FileStatistics stat = new FileStatistics(textFile.getName(), textFile.getPath(),
                textFile.getTextLength(), textFile.getNumberOfLines(), textFile.getLines());      
        try (Session session = SessionManager.getMysqlSession()) {
            session.beginTransaction();
            session.saveOrUpdate(stat);
            session.getTransaction().commit();
        }
    }
    
    public FileStatistics getTextFileByName(String name) {
        FileStatistics textFile = null;
        try (Session session = SessionManager.getMysqlSession()) {
            Criteria criteria = session.createCriteria(FileStatistics.class);
            textFile = (FileStatistics) criteria.add(Restrictions.eq("name", name))
                             .uniqueResult();
        }
        return textFile;
    }
    
    public void deleteTextFile(FileStatistics textFile) {
        try (Session session = SessionManager.getMysqlSession()) {
            session.beginTransaction();
            session.delete(textFile);
            session.getTransaction().commit();
        }
    }
}
