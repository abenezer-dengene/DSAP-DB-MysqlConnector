package dsapdbmysqlconnector.main.repository;

import dsapdbmysqlconnector.main.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, String> {
}
