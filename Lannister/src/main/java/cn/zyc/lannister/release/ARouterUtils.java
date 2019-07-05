package cn.zyc.lannister.release;

import cn.zyc.westeros.ARouter;
import cn.zyc.westeros.IRouter;

/**
 * Created by zhaoyuanchao on 2019-07-05.
 */
public class ARouterUtils implements IRouter {
    @Override
    public void putActivity() {
        ARouter.getInstance().putActivity("lannister/lannister",LannisterActivity.class);
    }
}
