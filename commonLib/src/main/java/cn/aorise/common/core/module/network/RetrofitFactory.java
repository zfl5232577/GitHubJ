package cn.aorise.common.core.module.network;


import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : tangjy
 *     e-mail : jianye.tang@aorise.org
 *     time   : 2017/11/29
 *     desc   : 联网请求
 *     version: 1.0
 * </pre>
 */
public class RetrofitFactory {
    private static RetrofitFactory sInstance;
    private HashMap<String, Object> services = new HashMap<>();
    private Retrofit mRetrofit;

    public static RetrofitFactory getInstance() {
        if (sInstance == null) {
            Class var0 = RetrofitFactory.class;
            synchronized (RetrofitFactory.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitFactory();
                }
            }
        }

        return sInstance;
    }

    private RetrofitFactory() {
    }

    public OkHttpClient getOkHttpsClient(boolean debug) {
        OkHttpClient.Builder mHttpBuilder = new OkHttpClient().newBuilder();
        try {
            X509TrustManager manager = new X509TrustManager() {
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }

                public void checkClientTrusted(javax.security.cert.X509Certificate[] chain, String authType) throws javax.security.cert.CertificateException {
                }

                public void checkServerTrusted(javax.security.cert.X509Certificate[] chain, String authType) throws javax.security.cert.CertificateException {
                }
            };
            TrustManager[] trustAllCerts = new TrustManager[]{manager};
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init((KeyManager[]) null, trustAllCerts, new SecureRandom());
            SSLSocketFactory factory = sc.getSocketFactory();
            mHttpBuilder.connectTimeout(60L, TimeUnit.SECONDS).writeTimeout(60L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).sslSocketFactory(factory, manager).hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            if (debug) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                mHttpBuilder.addInterceptor(interceptor);
            }
            // this.mHttpBuilder.addInterceptor(mSessionInterceptor);

            return mHttpBuilder.build();
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public <T> T create(Class<T> service, String uri,boolean debug) {
        Object apiService = services.get(uri);
        if (apiService == null) {
            synchronized (services) {
                apiService = services.get(uri);
                if (apiService == null) {
                    mRetrofit = new retrofit2.Retrofit.Builder().baseUrl(uri)
                            .client(getOkHttpsClient(debug))
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
                    apiService = mRetrofit.create(service);
                    services.put(uri, apiService);
                }
            }
        }
        return (T) apiService;
    }
}
