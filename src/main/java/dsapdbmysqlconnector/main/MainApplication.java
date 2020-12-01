package dsapdbmysqlconnector.main;

import dsapdbmysqlconnector.main.DbOperation.DbConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {


    @Autowired
    DbConnection dbConnection;

    public static void main(String[] args) {



        //save basic data to sys_table
        SpringApplication.run(MainApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        System.out.println("data source1 :"+dbConnection.getDbConnection().toString());
//        System.out.println("data source3 :"+dbConnection.getDbConnection().getSchema());
//        System.out.println("data source3 :"+dbConnection.getDbConnection().getCatalog());
//
//        PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement("SELECT * FROM DSA_TABLE");
//        PreparedStatement preparedStatement2 = dbConnection.getDbConnection().prepareStatement("CREATE TABLE `dsap_db`.`new_table` (`idnew_table` INT NOT NULL);");
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        ResultSet resultSet2 = preparedStatement.executeQuery();
//
//            System.out.println("id " + resultSet2.toString());
//
//        System.out.println("data source3 :"+dataSource.getConnection().createStatement().toString());
//        System.out.println("data source3 :"+dataSource.getConnection().createStatement().execute("SELECT * FROM DSA_TABLE"));
//        System.out.println("data source3 :"+dataSource.getConnection().createStatement().execute("CREATE TABLE `dsap_db`.`new_table` (`idnew_table` INT NOT NULL);"));

    }


}
