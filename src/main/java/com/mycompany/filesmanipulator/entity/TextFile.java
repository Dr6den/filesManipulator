package com.mycompany.filesmanipulator.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrew
 */
@XmlRootElement(name = "file")
public class TextFile {
    private String name;
    private String path;
    private int textLength;
    private int numberOfLines;
    @XmlElement(required = true)
    private List<String> lines;
    
    public TextFile(List<String> lines) {
        this.lines = lines;
        this.numberOfLines = lines.size();
        this.textLength = lines.stream().mapToInt((String s) -> s.length()).sum();
    }

    public TextFile() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTextLength() {
        return textLength;
    }

    public void setTextLength(int textLength) {
        this.textLength = textLength;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
    
}
