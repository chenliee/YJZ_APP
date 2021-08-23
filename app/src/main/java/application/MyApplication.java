package application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplication extends Application {
    private boolean isDebugARouter = true;
    @Override
    public void onCreate() {
        super.onCreate();
        if(isDebugARouter) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

}
