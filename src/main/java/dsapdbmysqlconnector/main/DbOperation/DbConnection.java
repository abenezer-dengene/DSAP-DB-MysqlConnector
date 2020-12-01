package dsapdbmysqlconnector.main.DbOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DbConnection {

    @Autowired
    DataSource dataSource;

    @Bean
    public Connection getDbConnection() {

        try {
           return dataSource.getConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
