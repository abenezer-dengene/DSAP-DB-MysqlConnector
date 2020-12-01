package dsapdbmysqlconnector.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "dsa_table")
@Data
public class Table extends BasicFieldType implements Serializable {

    @Column(name = "dsa_table_name", unique = true, nullable = false)
    private String dsaTableName;

    @OneToOne(cascade = CascadeType.ALL)
    private Label dsaLabel;

    @Column(name = "dsa_application")
    private String dsaApplication;

    @Column(name = "dsa_extends")
    private String dsaExtends;

    @Column(name = "dsa_extend_table")
    private boolean dsaExtendTable;

    @Column(name = "dsa_auto_number")
    private boolean dsaAutoNumber;

    @Column(name = "dsa_display_field")
    private String dsaDisplayField;

    @Column(name = "dsa_sys_attribute")
    private String dsaSysAttribute;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TableField> fieldList;

    public Table() {
        super();
    }

    public Table(String dsaTableName, Label dsaLabel, String dsaApplication,
                 String dsaExtends, boolean dsaExtendTable, boolean dsaAutoNumber,
                 String dsaDisplayField, String dsaSysAttribute) {
        this.dsaTableName = dsaTableName;
        this.dsaLabel = dsaLabel;
        this.dsaApplication = dsaApplication;
        this.dsaExtends = dsaExtends;
        this.dsaExtendTable = dsaExtendTable;
        this.dsaAutoNumber = dsaAutoNumber;
        this.dsaDisplayField = dsaDisplayField;
        this.dsaSysAttribute = dsaSysAttribute;
    }
}
