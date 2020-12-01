package dsapdbmysqlconnector.main.DbOperation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dsapdbmysqlconnector.main.model.BasicFieldType;
import dsapdbmysqlconnector.main.model.Table;
import dsapdbmysqlconnector.main.repository.TableRepository;
import dsapdbmysqlconnector.main.utility.Constant;
import dsapdbmysqlconnector.main.utility.ErrorLogger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


@Component
public class CreateOperation {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    DbConnection dbConnection;
    String sqlQuery = "";


    /**
     * Method used to create table in the database by accepting table stracture in the JSON format
     *
     * @param {JSONObject} jsonTable - table stracture and properties in josn format
     * @return return Map<String, Object> - hold detail information
     * -@isCreated - boolean value hold wheather table is created or not
     * -@error(optinal)- if error occure while creating error massage will be setted here
     * -@date - hold saved record
     */
    public Map<String, Object> createTable(JSONObject jsonTable) {

        Map<String, Object> map = new HashMap<>();
        try {

            String sqlQuery = queryBuilder(jsonTable, map);

            PreparedStatement statement = dbConnection.getDbConnection().prepareStatement(sqlQuery);
            statement.execute();

            indexTableinDsaTable(jsonTable,map);


        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "error occure while creating table");
            map.put("errorMassage", e.getMessage());
        }

        return map;

    }


    public void indexTableinDsaTable(JSONObject tableJson, Map<String,Object> map) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Table table = objectMapper.readValue(tableJson.toString(),Table.class);
            table = tableRepository.save(table);

            map.put("data",table);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error","Error in indexing table to dsa_table table");
            map.put("ErrorMassage",e.getMessage());
        }

    }

    /**
     * method used to build sql query for table create operation
     *
     * @param {JSONObject} tableObject - table information and field list
     * @return {String} - formated SQLquery string
     */
    public String queryBuilder(JSONObject tableObject, Map<String, Object> map) {


        // get table name from jsonObject
        String tableName = tableObject.optString("dsaTableName");

        // check tableName is exist in jsonObject
        if (tableName == null || tableName.isEmpty()) {
            map.put("error", "Invalide table name");
            return null;
        }

        // start building query by concating table name
        sqlQuery = " CREATE TABLE " + tableName + " ( ";

        // concat basic field type(dsa_id,dsa_created_at,dsa_created_by,dsa_update_count,dsa_updated_at , dsa_updated_by)
        sqlQuery += BasicFieldType.queryStatment();


        // get field list
        JSONArray fieldList = tableObject.getJSONArray("fieldList");
        fieldList.forEach(obj -> {
            //convert to jsonObject
            JSONObject field = new JSONObject(obj.toString());

            //get field name from json
            String fieldName = field.optString("dsaFieldName");
            if (fieldName == null || fieldName.isEmpty()) {
                map.put("errorMassage", map.get("errorMassage") + "\n" + "Invalide field name");
                return;
            }

            // get field Type

            String fieldType = getType(field.optJSONObject("dsaType"), map);
            if (fieldType == null || fieldType.isEmpty())
                return;

            fieldType = setFieldLength(field, fieldType);

            sqlQuery += fieldName + "  " + fieldType + " ";
            // check the lenth is specified in the json, if specified concat length to sqlQuery string

            if (field.has("dsaUnique") && field.optBoolean("dsaUnique"))
                sqlQuery += " UNIQUE , ";
            else {
                sqlQuery += " , ";
            }

        });


        sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 2) + ")";
        System.out.println("sql :  " + sqlQuery);


        return sqlQuery;
    }


    /**
     * set field value length
     */
    private String setFieldLength(JSONObject field, String fieldType) {
        if (field.has("dsaLength"))
            fieldType += "(" + field.get("dsaLength") + ")";
        else {
            // if length is not specified set default length for varchar datatype
            if (fieldType.equalsIgnoreCase("varchar")) fieldType += "(" + Constant.VARCHAR_DEFAULT_LENGTH + ")";
            if (fieldType.equalsIgnoreCase("date")) fieldType += "(" + Constant.DATE_DEFAULT_LENGTH + ")";
            if (fieldType.equalsIgnoreCase("datetime")) fieldType += "(" + Constant.DATETIME_DEFAULT_LENGTH + ")";
        }

        return fieldType;
    }


    /**
     * method for geting database field type
     * <p>
     * The method accept dsa_id as parament and search record from from database dsa_type table.
     *
     * @param{String} fieldTypeID - dsa_id for field type. the dsa_id in the dsa_field_type table
     * @param{Map<String,Object} mpa - if error occure, error massage will write in this map
     * @return{String} - return basic database field type text ex. varchar, date, datetime
     */
    private String getType(JSONObject fieldTypeObject, Map<String, Object> map) {
        try {

            String fieldTypeID = fieldTypeObject.optString("dsaId");
            // build query string
            String query = "Select dsa_db_data_type from dsa_field_type where dsa_id = '" + fieldTypeID + "'";
            PreparedStatement statement = dbConnection.getDbConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            //check if resultset has value
            if (resultSet.next())
                return resultSet.getString("dsa_db_data_type");
            else
                map.put("error", "unable to field field type text");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorLogger.SendLog(e);
            map.put("errorMassage", map.get("errorMassage") + "\n" + e.getMessage());
        }
        return null;

    }
}

