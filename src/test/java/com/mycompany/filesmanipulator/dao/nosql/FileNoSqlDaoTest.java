package com.mycompany.filesmanipulator.dao.nosql;

import com.mycompany.filesmanipulator.dao.FileNoSqlDao;
import com.mycompany.filesmanipulator.entity.TextFile;
import java.util.ArrayList;
import java.util.List;
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
    }
}
