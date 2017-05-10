package com.mycompany.filesmanipulator.dao.nosql;

import com.mycompany.filesmanipulator.dao.FileNoSqlDao;
import com.mycompany.filesmanipulator.dao.entity.nosql.FileStatisticsNoSqlEntity;
import com.mycompany.filesmanipulator.entity.TextFile;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author andrew
 */
public class FileNoSqlDaoTest {
    @Test
    public void crudCassandraTest() {
        String name = "Nadia";
        TextFile textFile = new TextFile();
        textFile.setName("Nadia");
        textFile.setNumberOfLines(15);
        textFile.setPath("/home/user/");
        textFile.setTextLength(9);
        List<String> lines = new ArrayList<>();
        lines.add("start");
        lines.add("finish");
        textFile.setLines(lines);
        
        FileNoSqlDao dao = new FileNoSqlDao();
        dao.save(textFile);
        
        FileStatisticsNoSqlEntity savedTextFile = dao.getTextFileByName(name);
        assertNotNull(savedTextFile);
        assertNotNull(savedTextFile.getFileId());
        assertEquals(savedTextFile.getName(), name);
        assertEquals((int)savedTextFile.getNumberOfLines(), textFile.getNumberOfLines());
        assertEquals((int)savedTextFile.getTextLength(), textFile.getTextLength());
        assertEquals(savedTextFile.getPath(), textFile.getPath());
        
        dao.deleteTextFile(savedTextFile);
        savedTextFile = dao.getTextFileByName(name);
        assertNull(savedTextFile);
    }
    
    
}
