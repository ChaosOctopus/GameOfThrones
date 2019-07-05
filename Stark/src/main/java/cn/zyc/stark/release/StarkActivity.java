package cn.zyc.stark.release;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zyc.annotations.BindPath;
import cn.zyc.stark.R;
@BindPath("start/start")
public class StarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stark);
    }
}
