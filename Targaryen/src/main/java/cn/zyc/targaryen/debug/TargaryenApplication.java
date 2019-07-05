package cn.zyc.targaryen.debug;

import cn.zyc.westeros.ARouter;
import cn.zyc.westeros.BaseApplication;

/**
 * Created by zhaoyuanchao on 2019-06-18.
 */

/**
 *  TargaryenApplication
 *  作为模块单独运行的 入口
 *  切换到组件模块时 需要被删除掉 所以放在debug文件夹中
 */
public class TargaryenApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.getInstance().init(this);
    }
}
