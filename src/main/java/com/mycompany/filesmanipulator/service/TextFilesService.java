package com.mycompany.filesmanipulator.service;

import com.mycompany.filesmanipulator.dao.FileDao;
import com.mycompany.filesmanipulator.entity.TextFile;
import com.mycompany.filesmanipulator.util.FilesRetreiver;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author andrew
 */
@Named
public class TextFilesService {
    @Inject
    private FileDao fileDao;
    
    public List<TextFile> getAllFilesFromFolder() {
        List<TextFile> files = null;
        try {
            files = FilesRetreiver.getFilesFromFolder();
        } catch (URISyntaxException |IOException ex) {
            Logger.getLogger(TextFilesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return files;
    }
    
    public void saveFile(String text) {
        List<String> lines = new ArrayList<>();
        lines.add(text);
        TextFile file = new TextFile(lines);
        fileDao.save(file);
    }
}
