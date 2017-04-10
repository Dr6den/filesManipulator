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
    public void save(TextFile textFile) {System.out.println("1--------------------------------");
        FileStatistics stat = new FileStatistics(textFile.getName(), textFile.getPath(),
                textFile.getTextLength(), textFile.getNumberOfLines(), textFile.getLines());System.out.println("2--------------------------------");       
        try (Session session = SessionManager.getSession()) {System.out.println("3--------------------------------");
            session.beginTransaction();System.out.println("4--------------------------------");
            session.saveOrUpdate(stat);
            session.getTransaction().commit();
        }
    }
    
    public FileStatistics getTextFileByName(String name) {
        FileStatistics textFile = null;
        try (Session session = SessionManager.getSession()) {
            Criteria criteria = session.createCriteria(FileStatistics.class);
            textFile = (FileStatistics) criteria.add(Restrictions.eq("name", name))
                             .uniqueResult();
        }
        return textFile;
    }
    
    public void deleteTextFile(FileStatistics textFile) {
        try (Session session = SessionManager.getSession()) {
            session.beginTransaction();
            session.delete(textFile);
            session.getTransaction().commit();
        }
    }
}
