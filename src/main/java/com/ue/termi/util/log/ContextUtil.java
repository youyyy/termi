package com.ue.termi.util.log;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yilinjun
 * @date 2022/7/8 17:41
 */
public class ContextUtil {
    public interface Constant {
        String request = "request";
        String response = "response";
        String tid = "tid";
    }


    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(()-> new HashMap<>(10));

    private ContextUtil() {
    }

    public static Object get(String key) {
        return CONTEXT.get().get(key);
    }

    public static Map<String, Object> getAll() {
        return CONTEXT.get();
    }

    public static void remove(String key) {
        CONTEXT.get().remove(key);
    }

    public static void removeAll() {
        CONTEXT.remove();
    }

    public static void put(String key, Object value) {
        if (value == null) {
            return;
        }
        CONTEXT.get().put(key, value);
    }

    public static void putAll(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        map.forEach(ContextUtil::put);

    }

    public static Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();

        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = request.getParameter(name);

            parameters.put(name, value);
        }

        return parameters;
    }
}
