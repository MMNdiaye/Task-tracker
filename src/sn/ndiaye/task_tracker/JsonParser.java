package sn.ndiaye.task_tracker;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonParser {
    public static <T> List<T> toObject(String json, Class<T> objectClass) {
        List<T> objects = new ArrayList<>();
        json = json.replaceAll("[\\[\\]]", "");
        String[] jsonObjects = json.split("},");
        Arrays.stream(jsonObjects)
                .map(s -> s.replaceAll("[{}\"]", ""))
                .forEach(s -> {
                    try {
                        var object = objectClass.getConstructor().newInstance();
                        String[] fields = s.split(",");
                        for (var field : fields) {
                            var fieldName = field.replaceAll(":.*", "").trim();
                            var fieldValue = field.replaceAll(".*:", "").trim();
                            var setterParamType = fieldName.equals("id") ? Long.class : String.class;
                            var setter = objectClass.getMethod("set" +
                                            fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1),
                                    setterParamType);
                            if (setterParamType.equals(Long.class))
                                setter.invoke(object, Long.valueOf(fieldValue));
                            else
                                setter.invoke(object, fieldValue);
                        }
                        objects.add(object);

                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }

                });
        return objects;
    }

    public static <T> String toJson(T obj) {
        var methods = obj.getClass().getMethods();
        var jsonString = Arrays.stream(methods)
                .filter(method -> method.getName().matches("get[A-Z][a-z]*") && !method.getName().equals("getClass"))
                .map(method -> {
                    var fieldName = method.getName().substring(3);
                    fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
                    try {
                        var fieldValue = method.invoke(obj, null);
                        return "\"" + fieldName + "\"" + ": " + ((fieldValue instanceof Long) ? fieldValue : "\"" + fieldValue + "\"");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                })
                .reduce((string, string2) -> string + ", " + string2)
                .orElseThrow();
        jsonString = "{" + jsonString + "}";
        return jsonString;
    }
}
