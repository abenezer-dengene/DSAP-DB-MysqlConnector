package dsapdbmysqlconnector.main.utility;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class IDGenerator {

    public static String getNewUUID(Object object) {
        ObjectIdGenerators.UUIDGenerator generator = new ObjectIdGenerators.UUIDGenerator();
        return generator.generateId(object).toString();
    }
}
