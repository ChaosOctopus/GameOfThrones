package cn.zyc.lannister.release;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zyc.annotations.BindPath;
import cn.zyc.lannister.R;

@BindPath("Lannister")
public class LannisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lannister);
    }
}
