package com.mycompany.filesmanipulator.util;

import com.mycompany.filesmanipulator.entity.TextFile;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author andrew
 */
public class FilesRetreiverTest {
    
    public FilesRetreiverTest() {
    }
    
    @Test
    public void getFilesFromFolderTest() throws IOException, URISyntaxException {
        List<TextFile> files = FilesRetreiver.getFilesFromFolder();
        assertNotNull(files);
        assertTrue(files.size() > 0);
    }
}
