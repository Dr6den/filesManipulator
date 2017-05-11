package com.mycompany.filesmanipulator.dao.nosql;

import com.mycompany.filesmanipulator.dao.FileNoSqlDao;
import com.mycompany.filesmanipulator.dao.entity.nosql.FileStatisticsNoSqlEntity;
import com.mycompany.filesmanipulator.entity.TextFile;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jglue.cdiunit.CdiRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author andrew
 */
@RunWith(CdiRunner.class)
public class FileNoSqlDaoTest {
    @Inject
    private FileNoSqlDao fileNoSqlDao;
    
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
        
        fileNoSqlDao.save(textFile);
        
        FileStatisticsNoSqlEntity savedTextFile = fileNoSqlDao.getTextFileByName(name);
        assertNotNull(savedTextFile);
        assertNotNull(savedTextFile.getFileId());
        assertEquals(savedTextFile.getName(), name);
        assertEquals((int)savedTextFile.getNumberOfLines(), textFile.getNumberOfLines());
        assertEquals((int)savedTextFile.getTextLength(), textFile.getTextLength());
        assertEquals(savedTextFile.getPath(), textFile.getPath());
        
        List<String> savedLines = savedTextFile.getLines();
        assertEquals(savedLines.size(), 2);
        assertTrue(savedLines.contains("start"));
        assertTrue(savedLines.contains("finish"));
        
        fileNoSqlDao.deleteTextFile(savedTextFile);
        savedTextFile = fileNoSqlDao.getTextFileByName(name);
        assertNull(savedTextFile);
    }
    
    
}
