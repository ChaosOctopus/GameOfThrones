package cn.zyc.targaryen.release;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zyc.annotations.BindPath;
import cn.zyc.targaryen.R;
@BindPath("dragon")
public class DragonstoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragonstone);
    }
}
