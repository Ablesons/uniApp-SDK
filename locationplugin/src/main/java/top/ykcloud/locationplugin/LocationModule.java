package top.ykcloud.locationplugin;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hdgq.locationlib.LocationOpenApi;
import com.hdgq.locationlib.entity.ShippingNoteInfo;
import com.hdgq.locationlib.listener.OnResultListener;
import com.hdgq.locationlib.listener.OnSendResultListener;
import com.taobao.weex.bridge.JSCallback;

import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;
import top.ykcloud.locationplugin.entity.AuthParams;
import top.ykcloud.locationplugin.entity.PauseParams;
import top.ykcloud.locationplugin.entity.RestartParams;
import top.ykcloud.locationplugin.entity.SendParams;
import top.ykcloud.locationplugin.entity.StartParams;
import top.ykcloud.locationplugin.entity.StopParams;


import java.util.List;

public class LocationModule extends UniModule {

    /**
     * 测试SDK
     *
     * @param test       参数
     * @param jsCallback 回调
     */
    @UniJSMethod
    public void testText(JSONObject test, UniJSCallback jsCallback) {
        Log.v("LocationModule", "data:" + test);
        Toast.makeText(mUniSDKInstance.getContext(), "测试成功!", Toast.LENGTH_SHORT).show();
        if (jsCallback != null) {
            JSONObject data = new JSONObject();
            data.put("result", true);
            data.put("code", 200);
            data.put("message", "测试成功！");
            data.put("data", test);
            jsCallback.invoke(data);
        }
    }

    /**
     * SDK授权
     *
     * @param auth       参数
     * @param jsCallback 回调
     */
    @UniJSMethod
    public void auth(JSONObject auth, UniJSCallback jsCallback) {
        AuthParams authParams = JSON.parseObject(auth.toJSONString(), AuthParams.class);
        LocationOpenApi.auth(mUniSDKInstance.getContext(), authParams.getAppId(),
                authParams.getAppSecurity(), authParams.getEnterpriseSenderCode(),
                authParams.getEnvironment(),
                resultListener(jsCallback));
    }

    /**
     * 启用SDK服务
     *
     * @param start      参数
     * @param jsCallback 回调
     */
    @UniJSMethod
    public void start(JSONObject start, UniJSCallback jsCallback) {
        StartParams startParams = JSON.parseObject(start.toJSONString(), StartParams.class);
        LocationOpenApi.start(mUniSDKInstance.getContext(), startParams.getVehicleNumber(),
                startParams.getDriverName(), startParams.getRemark(),
                startParams.getShippingNoteInfos().toArray(new ShippingNoteInfo[0]),
                resultListener(jsCallback));
    }

    /**
     * SDK发送服务
     *
     * @param send       参数
     * @param jsCallback 回调
     */
    @UniJSMethod
    public void send(JSONObject send, UniJSCallback jsCallback) {
        SendParams sendParams = JSON.parseObject(send.toJSONString(), SendParams.class);
        LocationOpenApi.send(mUniSDKInstance.getContext(), sendParams.getVehicleNumber(),
                sendParams.getDriverName(), sendParams.getRemark(),
                sendParams.getShippingNoteInfos().toArray(new ShippingNoteInfo[0]),
                resultSendListener(jsCallback));
    }

    /**
     * 暂停SDK服务
     *
     * @param pause      参数
     * @param jsCallback 回调
     */
    @UniJSMethod
    public void pause(JSONObject pause, UniJSCallback jsCallback) {
        PauseParams pauseParams = JSON.parseObject(pause.toJSONString(), PauseParams.class);
        LocationOpenApi.pause(mUniSDKInstance.getContext(), pauseParams.getVehicleNumber(),
                pauseParams.getDriverName(), pauseParams.getRemark(),
                pauseParams.getShippingNoteInfos().toArray(new ShippingNoteInfo[0]),
                resultListener(jsCallback));
    }

    /**
     * 重启SDK服务
     *
     * @param restart    参数
     * @param jsCallback 回调
     */
    @UniJSMethod
    public void restart(JSONObject restart, UniJSCallback jsCallback) {
        RestartParams restartParams = JSON.parseObject(restart.toJSONString(), RestartParams.class);
        LocationOpenApi.restart(mUniSDKInstance.getContext(), restartParams.getVehicleNumber(),
                restartParams.getDriverName(), restartParams.getRemark(),
                restartParams.getShippingNoteInfos().toArray(new ShippingNoteInfo[0]),
                resultListener(jsCallback));
    }

    /**
     * 停止SDK服务
     *
     * @param stop       参数
     * @param jsCallback 回调
     */
    @UniJSMethod
    public void stop(JSONObject stop, UniJSCallback jsCallback) {
        StopParams stopParams = JSON.parseObject(stop.toJSONString(), StopParams.class);
        LocationOpenApi.stop(mUniSDKInstance.getContext(), stopParams.getVehicleNumber(),
                stopParams.getDriverName(), stopParams.getRemark(),
                stopParams.getShippingNoteInfos().toArray(new ShippingNoteInfo[0]),
                resultListener(jsCallback));
    }

    /**
     * 回调函数封装
     *
     * @param jsCallback 回调方法
     * @return
     */
    private OnResultListener resultListener(final JSCallback jsCallback) {
        return new OnResultListener() {
            @Override
            public void onFailure(String s, String s1) {
                Log.v("OnResultListener", "errorCode:" + s + " errorMsg:" + s1);
                if (jsCallback != null) {
                    JSONObject data = new JSONObject();
                    data.put("result", false);
                    data.put("errorCode", s);
                    data.put("errorMsg", s1);
                    data.put("shippingNoteInfos", "");
                    jsCallback.invoke(data);
                }
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                Log.v("OnResultListener", "onSuccess:" + list.size());
                if (jsCallback != null) {
                    JSONObject data = new JSONObject();
                    data.put("result", true);
                    data.put("errorCode", "0");
                    data.put("errorMsg", "");
                    data.put("shippingNoteInfos", list);
                    jsCallback.invoke(data);
                }
            }
        };
    }

    private OnSendResultListener resultSendListener(final JSCallback jsCallback) {
        return new OnSendResultListener() {
            @Override
            public void onFailure(String s, String s1, List<ShippingNoteInfo> list) {
                Log.v("OnSendResultListener", "errorCode:" + s + " errorMsg:" + s1);
                if (jsCallback != null) {
                    JSONObject data = new JSONObject();
                    data.put("result", false);
                    data.put("errorCode", s);
                    data.put("errorMsg", s1);
                    data.put("shippingNoteInfos", list);
                    jsCallback.invoke(data);
                }
            }

            @Override
            public void onSuccess(List<ShippingNoteInfo> list) {
                Log.v("OnSendResultListener", "onSuccess:" + list.size());
                if (jsCallback != null) {
                    JSONObject data = new JSONObject();
                    data.put("result", true);
                    data.put("errorCode", "0");
                    data.put("errorMsg", "");
                    data.put("shippingNoteInfos", list);
                    jsCallback.invoke(data);
                }
            }
        };
    }
}
