package top.ykcloud.locationplugin;

import android.app.Application;
import android.util.Log;

import com.hdgq.locationlib.LocationOpenApi;

import io.dcloud.weex.AppHookProxy;

public class LocationModuleAppProxy implements AppHookProxy {

    /**
     * SDK初始化
     *
     * @param application 系统
     */
    @Override
    public void onCreate(Application application) {
        Log.v("LocationModuleAppProxy", "onCreate");
        try {
            LocationOpenApi.init(application);
        } catch (Exception e) {
            Log.e("init", "onCreate", e);
        }
    }
}
