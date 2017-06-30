package youtu.android601.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by djf on 2016/8/20.
 */

public class JsonTool {
    public static Gson gson = new GsonBuilder().create();

    /**
     * 得到提交字符串
     *
     * @param postParameters
     * @return
     */
    public static String formatPostParmater(Hashtable<String, String> postParameters) {
        try {
            StringBuilder result = new StringBuilder();
            if (postParameters != null) {
                result.append("{");
                Iterator iter = postParameters.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    result.append("\"" + URLEncoder.encode(key) + "\":\"" + URLEncoder.encode(value) + "\"");
                    if (iter.hasNext()) {
                        result.append(",");
                    }
                }
                result.append("}");
                return result.toString();
            } else {
                return "{}";
            }
        } catch (Exception e) {
            return "{}";
        }
    }

    /**
     * @param <T>
     * @param <T>
     * @param jsonString
     * @param cls
     * @return
     */
    public static <t, T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 使用Gson进行解析 List<Object>
     *
     * @param <T>
     * @param jsonString
     * @param type//     new TypeToken<List<T>>() {}.getType()
     * @return
     */

    public static <T> List<T> getObjects(String jsonString, Type type) throws Exception {
        List<T> list = new ArrayList<T>();

        list = gson.fromJson(jsonString, type);

        return list;
    }

    public static List<String> getList(String jsonString) {
        List<String> list = new ArrayList<String>();
        try {
            list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Map<String, Object>> listKeyMaps(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            list = gson.fromJson(jsonString,
                    new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将数据转换成Map<String,Object>对象
     *
     * @param jsonString
     * @return
     * @hide 这个方法目前有bug  会抛出异常， ""不能保留
     */
    public static Map<String, String> getMap(String jsonString) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            map = gson.fromJson(jsonString,
                    new TypeToken<Map<String, Integer>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @param jsonString
     * @return
     */

    public static HashMap<String, HashMap<String, Float>> getHashMap(String jsonString) {

        HashMap<String, HashMap<String, Float>> hashMap = new HashMap<String, HashMap<String, Float>>();

        try {
            hashMap = gson.fromJson(jsonString,
                    new TypeToken<HashMap<String, HashMap<String, Float>>>() {
                    }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return hashMap;
    }
}

