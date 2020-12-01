package dsapdbmysqlconnector.main.model;

import dsapdbmysqlconnector.main.utility.IDGenerator;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@Getter
@Setter
public class BasicFieldType {


    @Id
    @Column(name = "dsa_id", nullable = false, unique = true)
    private String dsaId = IDGenerator.getNewUUID("id");

    @Column(name = "dsa_created_by")
    private String dsaCreatedBy;

    @Column(name = "dsa_updated_by")
    private String dsaUpdatedBy;

    @CreationTimestamp
    @Column(name = "dsa_created_at")
    private Timestamp dsaCreatedAt;

    @UpdateTimestamp
    @Column(name = "dsa_updated_at")
    private Timestamp dsaUpdatedAt;

    @Column(name = "dsa_update_count")
    private Integer dsaUpdateCount;

    public String getDsaId() {
        return dsaId;
    }

    public void setDsaId(String dsaId) {
        this.dsaId = dsaId;
    }

    @Override
    public String toString() {
        return "BasicFieldType{" +
                "dsaId='" + dsaId + '\'' +
                ", dsaCreatedBy='" + dsaCreatedBy + '\'' +
                ", dsaUpdatedBy='" + dsaUpdatedBy + '\'' +
                ", dsaCreatedAt=" + dsaCreatedAt +
                ", dsaUpdatedAt=" + dsaUpdatedAt +
                ", dsaUpdateCount=" + dsaUpdateCount +
                '}';
    }

    public static String queryStatment(){
        return "  `dsa_id` varchar(255) NOT NULL," +
                "  `dsa_created_at` datetime(6) DEFAULT NULL," +
                "  `dsa_created_by` varchar(255) DEFAULT NULL," +
                "  `dsa_update_count` int(11) DEFAULT NULL," +
                "  `dsa_updated_at` datetime(6) DEFAULT NULL," +
                "  `dsa_updated_by` varchar(255) DEFAULT NULL," +
                 "  PRIMARY KEY (`dsa_id`),";
    }

}
