package com.mycompany.filesmanipulator.dao.entity.nosql;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrew
 */
@Table(keyspace = "filesmanipulator", name = "file_statistics")
@XmlRootElement
public class FileStatisticsNoSqlEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @PartitionKey
    private UUID fileId;
    private String name;
    private String path;
    private Integer textLength;
    private Integer numberOfLines;

    public FileStatisticsNoSqlEntity(String name, String path, Integer textLength, Integer numberOfLines, List<String> lines) {
        this.fileId = UUIDs.timeBased();
        this.name = name;
        this.path = path;
        this.textLength = textLength;
        this.numberOfLines = numberOfLines;
    }

    public FileStatisticsNoSqlEntity() {
    }

    public FileStatisticsNoSqlEntity(UUID fileId) {
        this.fileId = UUIDs.timeBased();
    }

    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fileId != null ? fileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileStatisticsNoSqlEntity)) {
            return false;
        }
        FileStatisticsNoSqlEntity other = (FileStatisticsNoSqlEntity) object;
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
