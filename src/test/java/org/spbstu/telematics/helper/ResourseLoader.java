package org.spbstu.telematics.helper;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.spbstu.telematics.factoryPageObjects.entities.FieldData;
import org.spbstu.telematics.factoryPageObjects.entities.User;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;

@Getter
public class ResourseLoader {

    public static final String DATA_USER_JSON = "data/users.json";
    public static final String DATA_FIELD_JSON = "data/fieldData.json";

    private static Map<String, User> users;
    private static Map<String, FieldData> fieldData;

    static {
        try {
            loadUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            loadFieldData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadUsers() throws IOException {
        String rawData = getRawData(DATA_USER_JSON);
        Type type = new TypeToken<Map<String, User>>() {
        }.getType();
        users = new Gson().fromJson(rawData, type);
    }

    private static void loadFieldData() throws IOException {
        String rawData = getRawData(DATA_FIELD_JSON);
        Type type = new TypeToken<Map<String, FieldData>>() {
        }.getType();
        fieldData = new Gson().fromJson(rawData, type);
        for (String key : fieldData.keySet()) {
            fieldData.get(key).setRandomStrings();
        }
    }

    private static String getRawData(String dataJson) throws IOException {
        URL resourse = ResourseLoader.class.getClassLoader().getResource(dataJson);
        return IOUtils.toString(resourse, "utf-8");
    }

    public static User getUser(String key) {
        return users.get(key);
    }

    public static void generateRandomFieldData(String key){
        fieldData.get(key).setRandomStrings();
    }

    public static FieldData getFieldData(String key) {
        return fieldData.get(key);
    }
}
