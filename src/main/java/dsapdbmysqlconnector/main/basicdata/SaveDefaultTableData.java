package dsapdbmysqlconnector.main.basicdata;

import com.fasterxml.jackson.databind.json.JsonMapper;
import dsapdbmysqlconnector.main.model.FieldType;
import dsapdbmysqlconnector.main.model.Label;
import dsapdbmysqlconnector.main.model.Table;
import dsapdbmysqlconnector.main.model.TableField;
import dsapdbmysqlconnector.main.repository.FieldTypeRepository;
import dsapdbmysqlconnector.main.repository.TableFieldRepository;
import dsapdbmysqlconnector.main.repository.TableRepository;
import dsapdbmysqlconnector.main.utility.ErrorLogger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class is responsible for saving Default table data to dsa_table,
 * dsa_fields_table and dsa_field_type table in the database which suppose to run on start up
 */
@Component
public class SaveDefaultTableData implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private FieldTypeRepository fieldTypeRepository;

    @Autowired
    private TableFieldRepository tableFieldRepository;

    @Autowired
    private TableRepository tableRepository;


    List<FieldType> basicFieldType = new ArrayList<>();


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
//            saveBasicFieldTypeData();
//            saveBasicTableData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Save basic field type to dsa_field_type table
     */
    public void saveBasicFieldTypeData() {

        try {

            basicFieldType.add(new FieldType("String", "string","varchar"));
            basicFieldType.add(new FieldType("Integer", "integer", "int(11)"));
            basicFieldType.add(new FieldType("Date", "date", "date"));
            basicFieldType.add(new FieldType("Time", "time", "time(6)"));
            basicFieldType.add(new FieldType("DateTime", "datetime", "datetime(6)"));
            basicFieldType.add(new FieldType("booean", "boolean", "boolean"));
            basicFieldType.add(new FieldType("Character", "character", "char(1)"));
            basicFieldType.add(new FieldType("Chioce", "string", "varchar"));
            basicFieldType.add(new FieldType("Reference", "string", "varchar(32)"));
            basicFieldType.add(new FieldType("label", "string", "varchar(32)"));
            basicFieldType.add(new FieldType("Condition", "string", "varchar"));
            basicFieldType.add(new FieldType("list", "string", "varchar"));
            basicFieldType.add(new FieldType("MultiDimantional List", "string", "varchar"));
            basicFieldType.add(new FieldType("Json", "string", "varchar"));

            fieldTypeRepository.saveAll(basicFieldType);
            System.out.println("Field type successfully created");
        } catch (Exception e) {
            e.printStackTrace();
            ErrorLogger.SendLog(e);
        }
    }

    /**
     * save bacis table stractural data to dsa_table, dsa_field_table and dsa_label table
     * <p>
     * dsa_table store table properties
     * dsa_field_table store data of table fields and field properties
     * dsa_label stare table and field label
     */
    public void saveBasicTableData() {

        try {

            JSONArray defaultTableData = DefaultData.BASIC_TABLE_PROPERTIES;
            JsonMapper jsonMapper = new JsonMapper();
            Table[] tables = jsonMapper.readValue(defaultTableData.toString(), Table[].class);
            Set<Table> tableSet = new HashSet<>(Arrays.asList(tables));
            tableRepository.saveAll(tableSet);

            System.out.println("table in data: " + tables);
//
//            List<Table> tableList = new ArrayList<>();
//            for (int i = 0; i < defaultTableData.length(); i++) {
//                JSONObject tableProporty = defaultTableData.getJSONObject(i);
//
//                Table table = new Table(tableProporty.getString("tablename"), null, "Global", null, true, false, null, null);
//                table.setDsaLabel(new Label(table, null, tableProporty.getString("label"), null));
//
//                tableRepository.save(table);
//                List<TableField> fieldList = ContractFieldListObject(table, tableProporty.getJSONArray("fields"));
//                tableFieldRepository.saveAll(fieldList);
//                // tableList.add(table);
//            }
            System.out.println("reached here ==" + 6);

        } catch (Exception e) {
            e.printStackTrace();
            ErrorLogger.SendLog(e);
        }
    }


    /**
     * constact Fieldlist Object from Json
     */
    private List<TableField> ContractFieldListObject(Table sysTable, JSONArray jsonFieldproperties) {


        List<TableField> fields = new ArrayList<>();
        for (int i = 0; i < jsonFieldproperties.length(); i++) {
            JSONObject fieldProporty = jsonFieldproperties.getJSONObject(i);

            TableField field = new TableField(sysTable, fieldProporty.getString("name"), null,
                    basicFieldType.get(0), true, false, false, false, false,
                    null, null, 100, true);

            field.setDsaFieldLabel(new Label(sysTable, fieldProporty.getString("label"), null));

            fields.add(field);
        }

        return fields;
    }


}

