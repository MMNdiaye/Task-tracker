package sn.ndiaye.task_tracker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JsonParser {
    public static <T> List<T> toObjects(String listJson, Class<T> objectClass) {
        // From [{"f": val, "f2": val2}, {"f": val3}]
        // To list of formatted string "f:val, f2:val"  for easier to extract object
        listJson = listJson.replaceAll("[\\[\\]]", "");
        String[] objectJsons = listJson.split("},");
        return Arrays.stream(objectJsons)
                .map(s -> s.replaceAll("[{}\"]", ""))
                .map(s -> toObject(s, objectClass))
                .filter(Objects::nonNull)
                .toList();
    }

    public static <T> T toObject(String objectJson, Class<T> objectClass) {
        try {
            if (objectJson.isEmpty())
                return null;
            var object = objectClass.getConstructor().newInstance();
            String[] fields = objectJson.split(",");
            for (var field : fields) {
                var fieldName = field.replaceAll(":.*", "").trim();
                var fieldValue = field.replaceAll(".*: ", "").trim();
                var setter = getFieldSetter(fieldName, objectClass);
                var fieldType = setter.getParameters()[0].getType();
                setter.invoke(object, getTypedValue(fieldType, fieldValue));
            }
            return object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    private static Method getFieldSetter(String fieldName, Class<?> objectClass) {
        var fieldNameFormatInMethod = fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1);
        Class<?> fieldType = getFieldType(objectClass, fieldName);
        try {
            return objectClass.getMethod("set" + fieldNameFormatInMethod, fieldType);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    private static Class<?> getFieldType(Class<?> objectClass, String fieldName) {
        
        try {
            return objectClass.getDeclaredField(fieldName).getType();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getTypedValue(Class<?> fieldType, String fieldValue) {
        // Classes like LocalDateTime will crash if you pass them "null" to convert
        if (Objects.equals(fieldValue, "null"))
            return null;
        return fieldType == String.class ? fieldValue
                : fieldType == Long.class ? Long.parseLong(fieldValue)
                : fieldType == Integer.class ? Integer.parseInt(fieldValue)
                : fieldType == Double.class ? Double.parseDouble(fieldValue)
                : fieldType == Boolean.class ? Boolean.valueOf(fieldValue)
                : fieldType == LocalDateTime.class ? LocalDateTime.parse(fieldValue)
                :fieldType == TaskStatus.class ? TaskStatus.valueOf(fieldValue)
                : null;
    }

    public static <T> String toJsons(List<T> objs) {
        var jsons = objs.stream()
                .map(JsonParser::toJson)
                .reduce((json1, json2) -> json1 + ", " + json2)
                .orElse("");
        return "[" + jsons + "]";
    }

    public static <T> String toJson(T obj) {
        var methods = obj.getClass().getMethods();
        // Getter Methods translated to field name and field values obtained by calling these methods
        var jsonString = Arrays.stream(methods)
                .filter(method -> method.getName().matches("get[A-Z][A-Za-z]*") &&
                        !method.getName().equals("getClass"))
                .map(method -> toJsonFieldNameAndValueFormat(obj, method))
                .reduce((string, string2) -> string + ", " + string2)
                .orElseThrow();
        jsonString = "{" + jsonString + "}";
        return jsonString;
    }

    private static <T> String toJsonFieldNameAndValueFormat(T obj, Method method) {
        var fieldName = method.getName().substring(3);
        fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
        try {
            var fieldValue = method.invoke(obj, null);
            return "\"" + fieldName + "\"" + ": " + ((fieldValue instanceof Long) ? fieldValue : "\"" + fieldValue + "\"");
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
