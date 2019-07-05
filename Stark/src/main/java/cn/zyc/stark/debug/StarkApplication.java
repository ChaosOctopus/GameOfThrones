package cn.zyc.stark.debug;

import cn.zyc.westeros.ARouter;
import cn.zyc.westeros.BaseApplication;

/**
 * Created by zhaoyuanchao on 2019-06-18.
 */
public class StarkApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);
    }
}
