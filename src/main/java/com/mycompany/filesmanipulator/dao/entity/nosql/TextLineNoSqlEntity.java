package com.mycompany.filesmanipulator.dao.entity.nosql;

import com.datastax.driver.mapping.annotations.Table;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrew
 */
@Table(keyspace = "filesmanipulator", name = "file_statistics")
@XmlRootElement
public class TextLineNoSqlEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "line_id")
    private Integer lineId;
    private String line;

    public TextLineNoSqlEntity(String line) {
        this.line = line;
    }

    public TextLineNoSqlEntity() {
    }

    public TextLineNoSqlEntity(Integer lineId) {
        this.lineId = lineId;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getLine() {
        return line;
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
