package singerstone.com.superapp.https;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.treeholeview.TreeHoleAnimationView;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2017/9/27.
 * YY:909075276
 */

public class HttpsTestFragment extends BaseFragment {
    TreeHoleAnimationView treeholeProgressView;

    public static HttpsTestFragment newInstance() {
        HttpsTestFragment fragment = new HttpsTestFragment();
        return fragment;
    }

    public HttpsTestFragment() {

    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_https, container, false);
        readHttpsCer();

        OkHttpClient client = createOkHttp();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("https://116.196.72.234:3000/").build();//2.

        Call call = client.newCall(request);//3.
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.e(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                L.i(response.body().string());

            }
        });
        return view;
    }

    //获取证书流
    private void readHttpsCer() {
        try {
            InputStream is = getActivity().getAssets().open("cers/srca.cer");
            NetConfig.addCertificate(is); // 这里将证书读取出来，，放在配置中byte[]里
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static OkHttpClient createOkHttp() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 添加证书
        List<InputStream> certificates = new ArrayList<>();
        List<byte[]> certs_data = NetConfig.getCertificatesData();

        // 将字节数组转为数组输入流
        if (certs_data != null && !certs_data.isEmpty()) {
            for (byte[] bytes : certs_data) {
                certificates.add(new ByteArrayInputStream(bytes));
            }
        }


        SSLSocketFactory sslSocketFactory = getSocketFactory(certificates);
        if (sslSocketFactory != null) {
            builder.sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(new HostnameVerifier() {//信任所有证书（不安全）
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            //手动验证一下hostname
                            if (hostname.equals("116.196.72.234")) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
        }

        return builder.build();
    }


    /**
     * 添加证书
     *
     * @param certificates
     */
    private static SSLSocketFactory getSocketFactory(List<InputStream> certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            try {
                for (int i = 0, size = certificates.size(); i < size; ) {
                    InputStream certificate = certificates.get(i);
                    String certificateAlias = Integer.toString(i++);
                    keyStore.setCertificateEntry(certificateAlias, certificateFactory
                            .generateCertificate(certificate));
                    if (certificate != null) {
                        certificate.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
