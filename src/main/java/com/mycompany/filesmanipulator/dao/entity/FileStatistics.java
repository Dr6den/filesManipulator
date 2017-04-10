package com.mycompany.filesmanipulator.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author andrew
 */
@Entity
@Table(name = "file_statistics", catalog = "text_files_statistics", schema = "")
@XmlRootElement
public class FileStatistics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "file_id")
    private Integer fileId;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 200)
    @Column(name = "path")
    private String path;
    @Column(name = "text_length")
    private Integer textLength;
    @Column(name = "number_of_lines")
    private Integer numberOfLines;
    @OneToMany(targetEntity = TextLine.class, mappedBy="textId", fetch = FetchType.EAGER,  cascade=CascadeType.ALL)
    private Collection<TextLine> textLineCollection;

    public FileStatistics(String name, String path, Integer textLength, Integer numberOfLines, List<String> lines) {
        this.name = name;
        this.path = path;
        this.textLength = textLength;
        this.numberOfLines = numberOfLines;
        Collection<TextLine> textLineCollection = new ArrayList<>();
        lines.stream().map((line) -> new TextLine(line)).forEachOrdered((textLine) -> {
            textLine.setTextId(this);
            textLineCollection.add(textLine);
        });
        this.textLineCollection = textLineCollection;
    }

    public FileStatistics() {
    }

    public FileStatistics(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getTextLength() {
        return textLength;
    }

    public void setTextLength(Integer textLength) {
        this.textLength = textLength;
    }

    public Integer getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(Integer numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    @XmlTransient
    public Collection<TextLine> getTextLineCollection() {
        return textLineCollection;
    }

    public void setTextLineCollection(Collection<TextLine> textLineCollection) {
        this.textLineCollection = textLineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileStatistics)) {
            return false;
        }
        FileStatistics other = (FileStatistics) object;
        if ((this.fileId == null && other.fileId != null) || (this.fileId != null && !this.fileId.equals(other.fileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.filesmanipulator.dao.entity.FileStatistics_1[ fileId=" + fileId + " ]";
    }
    
}
