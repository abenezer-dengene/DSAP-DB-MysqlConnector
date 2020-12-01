package dsapdbmysqlconnector.main.repository;

import dsapdbmysqlconnector.main.model.FieldType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldTypeRepository extends JpaRepository<FieldType, String> {
}
