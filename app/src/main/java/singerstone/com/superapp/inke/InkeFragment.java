package singerstone.com.superapp.inke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import singerstone.com.superapp.R;
import singerstone.com.superapp.base.BaseFragment;
import singerstone.com.superapp.utils.L;

/**
 * Created by chenbinhao on 2018/1/2.
 * YY:909075276
 */

public class InkeFragment extends BaseFragment {
    EditText et_question;
    EditText et_answer;
    Button btn_answer;
    Button btn_getanswer;
    TextView tv_result;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // TODO: 2017/7/5 在这里设置传值
    public static InkeFragment newInstance() {
        InkeFragment fragment = new InkeFragment();
        return fragment;
    }

    public InkeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inke, container, false);
        et_answer = (EditText) view.findViewById(R.id.et_answer);
        et_question = (EditText) view.findViewById(R.id.et_question);
        btn_answer = (Button) view.findViewById(R.id.btn_answer);
        btn_getanswer = (Button) view.findViewById(R.id.btn_get_answer);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        btn_getanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {

                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        String questionNum = et_question.getText().toString();
                        String url_get = "https://baseapi.busi.inke.cn/live/answer_subject?trace=" + questionNum + "_answer&lc=3289f2910aa0f341&cv=IK5.1.10_Android&cc=TG36013&ua=motorolaMotoXPro&uid=622931968&sid=20uKi000b73ksR7eJ099i1AtakZPYoce9pHH6lgZqWAPU0Av1GOU&devi=355464060295556&imsi=&imei=355464060295556&icc=&conn=wifi&vv=1.0.3-201610121413.android&aid=29eeaa80401c3ab&osversion=android_21&proto=8&smid=DuUDisNCJStHKvOqfbIDKzvGOeC7sye%2B0D6vKE56P7fkiWK3eNHtPLfNQuJuHj%2Bf0DzvnKTAWuUHdt0d7qdLGOoA&mtid=d24eef06725ee2c7845a227706adf216&mtxid=3891d5846040&logid=270%2C210%2C236%2C243&ast=1";
                        L.e(url_get);
                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url(url_get)
                                .build();
                        Call call = okHttpClient.newCall(request);
                        final Response response = call.execute();
                        e.onNext(response.body().string());
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                if (tv_result != null) {
                                    if (tv_result != null) {
                                        L.e(s);
                                        Gson gson = new Gson();
                                        JsonRootBean bean = gson.fromJson(s, JsonRootBean.class);
                                        tv_result.setText(bean.getData().getSubject_title() + "\n" + bean.getData().getAnswer_info().getRight_answer());
                                    }
                                }
                            }
                        });


            }
        });

        btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {

                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        String questionNum = et_question.getText().toString();
                        String answer = et_answer.getText().toString();
                        String url = "https://baseapi.busi.inke.cn/live/answer?trace" + questionNum + "=&lc=3289f2910aa0f341&cv=IK5.1.10_Android&cc=TG36013&ua=motorolaMotoXPro&uid=622931968&sid=20uKi000b73ksR7eJ099i1AtakZPYoce9pHH6lgZqWAPU0Av1GOU&devi=355464060295556&imsi=&imei=355464060295556&icc=&conn=wifi&vv=1.0.3-201610121413.android&aid=29eeaa80401c3ab&osversion=android_21&proto=8&smid=DuUDisNCJStHKvOqfbIDKzvGOeC7sye%2B0D6vKE56P7fkiWK3eNHtPLfNQuJuHj%2Bf0DzvnKTAWuUHdt0d7qdLGOoA&mtid=d24eef06725ee2c7845a227706adf216&mtxid=3891d5846040&logid=270%2C210%2C236%2C243&ast=1";
                        L.e(url);
                        InkeAnswer inkeAnswer = new InkeAnswer();
                        inkeAnswer.setUid("622931968");
                        inkeAnswer.setSid("20uKi000b73ksR7eJ099i1AtakZPYoce9pHH6lgZqWAPU0Av1GOU");
                        inkeAnswer.setSubject_id(Integer.valueOf(questionNum));
                        inkeAnswer.setAnswer(answer);
                        L.e(new Gson().toJson(inkeAnswer));
                        RequestBody requestBody = RequestBody.create(JSON, new Gson().toJson(inkeAnswer));
                        OkHttpClient okHttpClient = new OkHttpClient();

                        Request request = new Request.Builder()
                                .url(url)
                                .post(requestBody)
                                .build();

                        Call call = okHttpClient.newCall(request);

                        final Response response = call.execute();
                        e.onNext(response.body().string());


                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                if (tv_result != null) {
                                    if (tv_result != null) {
                                        // Gson gson = new Gson();
                                        // JsonRootBean bean = gson.fromJson(s, JsonRootBean.class);
                                        // tv_result.setText(bean.getData().getSubject_title()+"\n"+bean.getData().getAnswer_info().getRight_answer());
                                        L.e(s);
                                        tv_result.setText(new Gson().fromJson(s, Result.class).getMessage());
                                    }
                                }
                            }
                        });


            }


        });
        return view;
    }

    public static String decodeUnicode(final String unicode) {
        String str = "";
        for (int i = 0; i < unicode.length(); i += 4) {
            String s = "";
            for (int j = i; j < i + 4; j++) {
                s += String.valueOf(unicode.charAt(j));
            }
            str += String.valueOf((char) Integer.valueOf(s, 16).intValue());
        }
        return str;
    }
}
