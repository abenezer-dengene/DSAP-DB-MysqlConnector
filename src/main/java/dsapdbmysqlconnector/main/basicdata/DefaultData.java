package dsapdbmysqlconnector.main.basicdata;

import org.json.JSONArray;
import org.json.JSONObject;


public class DefaultData {


    public static JSONArray BASIC_TABLE_PROPERTIES = new JSONArray()
            .put(new JSONObject()
                    .put("dsaTableName", "dsa_table")
                    .put("dsaLabel", new JSONObject().put("dsaValue","table"))
                    .put("fieldList", new JSONArray()
                            .put(new JSONObject().put("dsaFieldName", "dsa_table_name").put("dsaFieldLabel", new JSONObject().put("dsaValue", "table name")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_label").put("dsaFieldLabel",  new JSONObject().put("dsaValue","dsaFieldLabel")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_application").put("dsaFieldLabel", new JSONObject().put("dsaValue", "application")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_extends").put("dsaFieldLabel", new JSONObject().put("dsaValue", "extends")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_extend_table").put("dsaFieldLabel", new JSONObject().put("dsaValue", "extend table")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_auto_number").put("dsaFieldLabel", new JSONObject().put("dsaValue", "auto number")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_display_field").put("dsaFieldLabel", new JSONObject().put("dsaValue", "display field")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_attribute").put("dsaFieldLabel", new JSONObject().put("dsaValue", "attribute")))))

            .put(new JSONObject()
                    .put("dsaTableName", "dsa_table_field")
                    .put("dsaLabel", new JSONObject().put("dsaValue","fields"))
                    .put("fieldList", new JSONArray()
                            .put(new JSONObject().put("dsaFieldName", "dsa_name").put("dsaFieldLabel",  new JSONObject().put("dsaValue","dsaFieldName")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_parent_field").put("dsaFieldLabel",  new JSONObject().put("dsaValue","parent field")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_html").put("dsaFieldLabel",  new JSONObject().put("dsaValue","html")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_script").put("dsaFieldLabel", new JSONObject().put("dsaValue", "script")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_css").put("dsaFieldLabel", new JSONObject().put("dsaValue" ,"css")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_active").put("dsaFieldLabel",  new JSONObject().put("dsaValue","active")))))

            .put(new JSONObject()
                    .put("dsaTableName", "dsa_label")
                    .put("dsaLabel", new JSONObject().put("dsaValue","dsaFieldLabel"))
                    .put("fieldList", new JSONArray()
                            .put(new JSONObject().put("dsaFieldName", "dsa_table").put("dsaFieldLabel",  new JSONObject().put("dsaValue","table")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_field").put("dsaFieldLabel",  new JSONObject().put("dsaValue","field")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_value").put("dsaFieldLabel",  new JSONObject().put("dsaValue","value")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_language").put("dsaFieldLabel", new JSONObject().put("dsaValue", "language")))))

            .put(new JSONObject()
                    .put("dsaTableName", "dsa_feild_type")
                    .put("dsaLabel", new JSONObject().put("dsaValue","field type"))
                    .put("fieldList", new JSONArray()
                            .put(new JSONObject().put("dsaFieldName", "dsa_table").put("dsaFieldLabel",  new JSONObject().put("dsaValue", "table")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_field_name").put("dsaFieldLabel",  new JSONObject().put("dsaValue", "field name")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_label").put("dsaFieldLabel", new JSONObject().put("dsaValue", "dsaFieldLabel")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_type").put("dsaFieldLabel", new JSONObject().put("dsaValue", "type")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_active").put("dsaFieldLabel",  new JSONObject().put("dsaValue","active")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_readonly").put("dsaFieldLabel", new JSONObject().put("dsaValue", "readonly")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_mandatory").put("dsaFieldLabel", new JSONObject().put("dsaValue", "mandatory")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_unique").put("dsaFieldLabel", new JSONObject().put("dsaValue", "unique")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_display").put("dsaFieldLabel", new JSONObject().put("dsaValue", "display")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_reference").put("dsaFieldLabel", new JSONObject().put("dsaValue", "reference")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_choice").put("dsaFieldLabel", new JSONObject().put("dsaValue", "choice")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_length").put("dsaFieldLabel", new JSONObject().put("dsaValue", "lenth")))
                            .put(new JSONObject().put("dsaFieldName", "dsa_visible").put("dsaFieldLabel", new JSONObject().put("dsaValue", "visible")))));

}
