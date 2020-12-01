package dsapdbmysqlconnector.main.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity(name = "dsa_label")
@Data
public class Label extends BasicFieldType implements Serializable {


    @OneToOne()
    private Table dsaTable;

    @OneToOne
    private TableField dsaField;

    @Column(name = "dsa_value")
    private String dsaValue;

    @Column(name = "dsa_languge")
    private String dsaLanguge;


    public Label() {
    }

    public Label(Table dsaTable, TableField dsaField, String dsaValue, String dsaLanguge) {
        this.dsaTable = dsaTable;
        this.dsaField = dsaField;
        this.dsaValue = dsaValue;
        this.dsaLanguge = dsaLanguge;
    }

    public Label(Table dsaTable, String dsaValue, String dsaLanguge) {
        this.dsaTable = dsaTable;
        this.dsaValue = dsaValue;
        this.dsaLanguge = dsaLanguge;
    }
}
