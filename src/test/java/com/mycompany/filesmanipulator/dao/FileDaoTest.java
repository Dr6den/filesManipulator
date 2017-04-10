package com.mycompany.filesmanipulator.dao;

import com.mycompany.filesmanipulator.dao.entity.FileStatistics;
import com.mycompany.filesmanipulator.dao.entity.TextLine;
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
public class FileDaoTest {
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
        
        FileDao dao = new FileDao();
        dao.save(textFile);
        
        FileStatistics stat = dao.getTextFileByName(name);
        List<TextLine> savedLines = (List<TextLine>) stat.getTextLineCollection();
        assertNotNull(stat);
        assertNotNull(savedLines);
        assertEquals(savedLines.size(), 2);
        assertEquals(stat.getTextLength(), new Integer(9));
    }
    
    @Test
    public void deleteFileTest() {
        String name = "Nadia";
        FileDao dao = new FileDao();        
        FileStatistics stat = dao.getTextFileByName(name);
        assertNotNull(stat);
        
        dao.deleteTextFile(stat);
        stat = dao.getTextFileByName(name);
        assertNull(stat);
    }
}
