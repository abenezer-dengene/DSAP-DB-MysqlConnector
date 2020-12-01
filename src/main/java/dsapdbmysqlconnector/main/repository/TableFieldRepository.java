package dsapdbmysqlconnector.main.repository;

import dsapdbmysqlconnector.main.model.TableField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableFieldRepository extends JpaRepository<TableField, String> {
}
