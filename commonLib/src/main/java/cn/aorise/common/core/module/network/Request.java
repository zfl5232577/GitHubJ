package cn.aorise.common.core.module.network;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.aorise.common.core.util.GsonUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * <pre>
 *     author : tangjy
 *     e-mail : jianye.tang@aorise.org
 *     time   : 2017/09/14
 *     desc   : 统一的联网请求实体
 *     version: 1.0
 * </pre>
 */
public class Request implements Serializable {
    public Request() {
    }

    public String toJson() {
        return GsonUtils.toJson(this);
    }

    public Map<String, String> toQueryMap() {
        Map<String, String> queryMap = new HashMap<>();
        Json2Map(null, queryMap, toJson());
        return queryMap;
    }

    public RequestBody toRequestBody() {
        return RequestBody.create(MediaType.parse("application/json"), this.toJson());
    }

    /**
     * @param fatherKey 当前层级的KEY第一层为null
     * @param queryMap 输出Map
     * @param jsonStr 对象转成的Json字符串
     */
    private void Json2Map(String fatherKey, Map<String, String> queryMap, String jsonStr) {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = jsonObject.get(key).toString();
                if (value.startsWith("{") && value.endsWith("}")) {
                    if (TextUtils.isEmpty(fatherKey)) {
                        Json2Map(key, queryMap, value);
                    } else {
                        Json2Map(fatherKey + "." + key, queryMap, value);
                    }
                } else {
                    if (TextUtils.isEmpty(fatherKey)) {
                        queryMap.put(key, value);
                    } else {
                        queryMap.put(fatherKey + "." + key, value);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
