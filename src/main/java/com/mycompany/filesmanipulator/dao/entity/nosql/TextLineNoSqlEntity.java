package com.mycompany.filesmanipulator.dao.entity.nosql;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrew
 */
@Table(keyspace = "filesmanipulator", name = "file_statistics")
@XmlRootElement
public class TextLineNoSqlEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @PartitionKey(0)
    @Column(name="line_id")
    private UUID lineId;
    @PartitionKey(1)
    @Column(name="file_id")
    private UUID fileId;
    private String line;

    public TextLineNoSqlEntity(String line, UUID fileId) {
        this.lineId = UUIDs.timeBased();
        this.line = line;
        this.fileId = fileId;
    }

    public TextLineNoSqlEntity() {
    }

    public TextLineNoSqlEntity(UUID lineId, UUID fileId) {
        this.lineId = lineId;
        this.fileId = fileId;
    }

    public UUID getLineId() {
        return lineId;
    }

    public void setLineId(UUID lineId) {
        this.lineId = lineId;
    }

    public String getLine() {
        return line;
    }

    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineId != null ? lineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TextLineNoSqlEntity)) {
            return false;
        }
        TextLineNoSqlEntity other = (TextLineNoSqlEntity) object;
        if ((this.lineId == null && other.lineId != null) || (this.lineId != null && !this.lineId.equals(other.lineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.filesmanipulator.dao.entity.TextLine[ lineId=" + lineId + " ]";
    }
    
}
