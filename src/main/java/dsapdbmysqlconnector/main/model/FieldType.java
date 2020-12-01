package dsapdbmysqlconnector.main.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "dsa_field_type")
@Data
public class FieldType extends dsapdbmysqlconnector.main.model.BasicFieldType implements Serializable {


    @Column(name = "dsa_field_name")
    private String dsaFieldName;

    @Column(name = "dsa_parent_field")
    private String dsaParentField;

    @Column(name = "dsa_html")
    private String dsaHtml;

    @Column(name = "dsa_script")
    private String dsaScript;

    @Column(name = "dsa_css")
    private String dsaCss;

    @Column(name = "dsa_active")
    private boolean dsaActive=true;

    @Column(name = "dsa_db_data_type")
    private String dsaDbDataType;

    public FieldType() {

    }

    public FieldType(String dsaName, String dsaParentField, String dsaDbDataType) {
        this.dsaFieldName = dsaName;
        this.dsaParentField = dsaParentField;
        this.dsaDbDataType = dsaDbDataType;
    }
}
