package singerstone.com.superapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import singerstone.com.superapp.circlepkprogressView.CirclepkAnimationView;
import singerstone.com.superapp.circlepkprogressView.PKCoolingLayer;

public class TestActivity extends Activity {
    CirclepkAnimationView circlepkAnimationView;
    int count = 0;
    Button btn;
    Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btn = (Button) findViewById(R.id.btn);
        circlepkAnimationView = (CirclepkAnimationView) findViewById(R.id.circle_progress_animation_view);
        circlepkAnimationView.setOnCountDownFinishedListener(new PKCoolingLayer.OnCountDownFinishedListener() {
            @Override
            public void onCountDownFinished() {
                count = 0;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circlepkAnimationView.setCurrentGiftCount(count = count + 1);
            }
        });
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //circlepkAnimationView.resetView();
                circlepkAnimationView.setMaxCount(50);
            }
        });

    }
}
