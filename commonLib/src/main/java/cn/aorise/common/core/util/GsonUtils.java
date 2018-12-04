package cn.aorise.common.core.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


/**
 * <pre>
 *     author : tangjy
 *     e-mail : jianye.tang@aorise.org
 *     time   : 2017/03/17
 *     desc   : Gson单例操作
 *     version: 1.0
 * </pre>
 */
public class GsonUtils {
    private static Gson sGson = new Gson();

    private GsonUtils() {
        throw new AssertionError();
    }

    /**
     * Json数据转对象
     *
     * @param json    Json数据
     * @param typeOfT 类型
     * @param <T>     对象类型
     * @return
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return sGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            AoriseLog.e(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json数据转对象
     *
     * @param json Json数据
     * @param <T>  对象类型
     * @return
     */
    @Deprecated
    public static <T> T fromJson(String json) {
        try {
            return sGson.fromJson(json, new TypeToken<T>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            AoriseLog.e(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json数据转对象
     *
     * @param json     Json数据
     * @param classOfT 类型
     * @param <T>      对象类型
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return sGson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            AoriseLog.e(e.toString());
            e.printStackTrace();
        } catch (JsonIOException e) {
            AoriseLog.e(e.toString());
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Json对象转string
     *
     * @param object Json对象
     * @return string
     */
    public static String toJson(Object object) {
        return sGson.toJson(object);
    }
}
