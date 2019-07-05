package cn.zyc.targaryen.debug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.zyc.targaryen.R;

/**
 *  launcherActivity
 *  作为模块单独运行的 入口
 *  切换到组件模块时 需要被删除掉 所以放在debug文件夹中
 */

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }
}
