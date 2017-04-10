package com.mycompany.filesmanipulator.util;

import com.mycompany.filesmanipulator.entity.TextFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author andrew
 */
public class FilesRetreiver {
    public static String propPath = "src/main/resources/customization.properties";
    
    public static Properties readPropertiesFile() throws FileNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
        Properties prop = new Properties();
        InputStream input = classLoader.getResourceAsStream("customization.properties");
        prop.load(input);
        return prop;	
    }
    
    public static List<TextFile> getFilesFromFolder() throws IOException, URISyntaxException {
        List<TextFile> files = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URI uri = classLoader.getResource("customization.properties").toURI();
        Path folderPath = Paths.get(uri.getPath().substring(0, uri.getPath().lastIndexOf("/")));
        
        try (Stream<Path> paths = Files.walk(folderPath)) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    List<String> lines;
                    try {
                        lines = Files.readAllLines(filePath);
                        TextFile file = new TextFile(lines);
                        file.setName(filePath.getFileName().toString());
                        file.setPath(filePath.toString());
                        files.add(file);
                    } catch (IOException ex) {
                        Logger.getLogger(FilesRetreiver.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }
            });
        }
        return files;
    }
}
