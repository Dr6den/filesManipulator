package com.mycompany.filesmanipulator.dao.entity.nosql;

import com.mycompany.filesmanipulator.dao.entity.*;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andrew
 */
@Entity
@Table(name = "text_line", catalog = "text_files_statistics", schema = "")
@XmlRootElement
public class TextLineNoSqlEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "line_id")
    private Integer lineId;
    @Size(max = 200)
    @Column(name = "line")
    private String line;
    @JoinColumn(name = "text_id", referencedColumnName = "file_id")
    @ManyToOne
    private FileStatisticsNoSqlEntity textId;

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

    public FileStatisticsNoSqlEntity getTextId() {
        return textId;
    }

    public void setTextId(FileStatisticsNoSqlEntity textId) {
        this.textId = textId;
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
