package com.ue.termi.exception;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Collection;
import java.util.Map;


public class AssertUtil {
    private AssertUtil() {
        // 工具类不需要实例化
    }

    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) {
        if (!expression) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isFalse(boolean expression, String errorMsgTemplate, Object... params) {
        if (expression) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        }
    }


    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] - this expression must be false");
    }

    public static void isNull(Object object, String errorMsgTemplate, Object... params) {
        if (object != null) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    public static <T> T notNull(T object, String errorMsgTemplate, Object... params) {
        if (object == null) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return object;
        }
    }

    public static <T> T notNull(T object) {
        return notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static String notEmpty(String text, String errorMsgTemplate, Object... params) {
        if (StrUtil.isEmpty(text)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return text;
        }
    }

    public static String notEmpty(String text) {
        return notEmpty(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static String notBlank(String text, String errorMsgTemplate, Object... params) {
        if (StrUtil.isBlank(text)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return text;
        }
    }


    public static String notBlank(String text) {
        return notBlank(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    public static String notContain(String textToSearch, String substring, String errorMsgTemplate, Object... params) {
        if (StrUtil.isNotEmpty(textToSearch) && StrUtil.isNotEmpty(substring) && textToSearch.contains(substring)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return substring;
        }
    }

    public static String notContain(String textToSearch, String substring) {
        return notContain(textToSearch, substring, "[Assertion failed] - this String argument must not contain the substring [{}]", substring);
    }

    public static Object[] notEmpty(Object[] array, String errorMsgTemplate, Object... params) {
        if (ArrayUtil.isEmpty(array)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return array;
        }
    }

    public static Object[] notEmpty(Object[] array) {
        return notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    public static <T> T[] noNullElements(T[] array, String errorMsgTemplate, Object... params) {
        if (ArrayUtil.hasNull(array)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return array;
        }
    }

    public static <T> T[] noNullElements(T[] array) {
        return noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    public static <T> Collection<T> notEmpty(Collection<T> collection, String errorMsgTemplate, Object... params) {
        if (CollectionUtil.isEmpty(collection)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return collection;
        }
    }


    public static <T> Collection<T> notEmpty(Collection<T> collection) {
        return notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, String errorMsgTemplate, Object... params) {
        if (CollectionUtil.isEmpty(map)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return map;
        }
    }

    public static <K, V> Map<K, V> notEmpty(Map<K, V> map) {
        return notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static <T> T isInstanceOf(Class<?> type, T obj) {
        return isInstanceOf(type, obj, "Object [{}] is not instanceof [{}]", obj, type);
    }

    public static <T> T isInstanceOf(Class<?> type, T obj, String errorMsgTemplate, Object... params) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        } else {
            return obj;
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "{} is not assignable to {})", subType, superType);
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String errorMsgTemplate, Object... params) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    public static void state(boolean expression, String errorMsgTemplate, Object... params) {
        if (!expression) {
            throw new IException(StrUtil.format(errorMsgTemplate, params));
        }
    }

    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }
}
