package top.ykcloud.testplugin;

import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

public class TestModule extends UniModule {
    String NAME="name";
    String AGE ="age";

    @UniJSMethod(uiThread = true)
    public void testText(JSONObject options, UniJSCallback callBack){
        Log.e("TestModule", "成功调用!" );
        String name = options.getString(NAME);
        String age = options.getString(AGE);
        JSONObject data =new JSONObject();
        if (name !=null && !name.isEmpty() && age !=null && !age.isEmpty()){
            int _age = Integer.parseInt(age);
            if (_age<0 || _age>30){
                data.put("code","不合格!");
            }else {
                age = (_age>0 && _age<10) ? "0"+age:age;
                data.put("code","合格:"+"姓名_"+name+",年龄_"+age);
            }
        }else {
            data.put("code","输入无效!");
        }
        callBack.invoke(data);
    }
}
