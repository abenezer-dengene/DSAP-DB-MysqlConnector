package dsapdbmysqlconnector.main.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity(name = "dsa_table_field")
@Data
public class TableField extends BasicFieldType implements Serializable {


    @OneToOne
    private Table dsaTable;

    @Column(name = "dsa_field_name")
    private String dsaFieldName;

    @OneToOne(cascade = CascadeType.ALL)
    private Label dsaFieldLabel;

    @OneToOne
    private FieldType dsaType;

    @Column(name = "dsa_active")
    private boolean dsaActive;

    @Column(name = "dsa_readonly")
    private boolean dsaReadonly;

    @Column(name = "dsa_mandatory")
    private boolean dsaMandatory;

    @Column(name = "dsa_unique")
    private boolean dsaUnique;

    @Column(name = "dsa_display")
    private boolean dsaDisplay;

    @Column(name = "dsa_reference")
    private String dsaReference;

    @Column(name = "dsa_choice")
    private String dsaChoice;

    @Column(name = "dsa_length")
    private int dsaLength;

    @Column(name = "dsa_visible")
    private boolean dsaVisible;

    public TableField() {
        super();
    }

    public TableField(Table dsaTable, String dsaFieldName, Label dsaFieldLabel,
                      FieldType dsaType, boolean dsaActive, boolean dsaReadonly,
                      boolean dsaMandatory, boolean dsaUnique, boolean dsaDisplay,
                      String dsaReference, String dsaChoice, int dsaLength,
                      boolean dsaVisible) {
        this.dsaTable = dsaTable;
        this.dsaFieldName = dsaFieldName;
        this.dsaFieldLabel = dsaFieldLabel;
        this.dsaType = dsaType;
        this.dsaActive = dsaActive;
        this.dsaReadonly = dsaReadonly;
        this.dsaMandatory = dsaMandatory;
        this.dsaUnique = dsaUnique;
        this.dsaDisplay = dsaDisplay;
        this.dsaReference = dsaReference;
        this.dsaChoice = dsaChoice;
        this.dsaLength = dsaLength;
        this.dsaVisible = dsaVisible;

    }

    @Override
    public String toString() {
        return "TableField{" +
                "dsaTable=" + dsaTable +
                ", dsaFieldName='" + dsaFieldName + '\'' +
                ", dsaFieldLabel=" + dsaFieldLabel +
                ", dsaType=" + dsaType +
                ", dsaActive=" + dsaActive +
                ", dsaReadonly=" + dsaReadonly +
                ", dsaMandatory=" + dsaMandatory +
                ", dsaUnique=" + dsaUnique +
                ", dsaDisplay=" + dsaDisplay +
                ", dsaReference='" + dsaReference + '\'' +
                ", dsaChoice='" + dsaChoice + '\'' +
                ", dsaLength=" + dsaLength +
                ", dsaVisible=" + dsaVisible +
                '}';
    }
}
