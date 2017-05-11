package com.mycompany.filesmanipulator.dao;

import com.mycompany.filesmanipulator.dao.entity.FileStatistics;
import com.mycompany.filesmanipulator.dao.entity.TextLine;
import com.mycompany.filesmanipulator.entity.TextFile;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jglue.cdiunit.CdiRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author andrew
 */
@RunWith(CdiRunner.class)
public class FileDaoTest {
    @Inject
    private FileDao fileDao;
    
    @Test
    public void saveFileTest() {
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
        
        fileDao.save(textFile);
        
        FileStatistics stat = fileDao.getTextFileByName(name);
        List<TextLine> savedLines = (List<TextLine>) stat.getTextLineCollection();
        assertNotNull(stat);
        assertNotNull(savedLines);
        assertEquals(savedLines.size(), 2);
        assertEquals(stat.getTextLength(), new Integer(9));
    }
    
    @Test
    public void deleteFileTest() {
        String name = "Nadia";
        FileStatistics stat = fileDao.getTextFileByName(name);
        assertNotNull(stat);
        
        fileDao.deleteTextFile(stat);
        stat = fileDao.getTextFileByName(name);
        assertNull(stat);
    }
}
