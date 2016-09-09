package top.wefor.simplerxbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import top.wefor.simplerxbus.rxbus.RxBus;

/**
 * User: 吕勇
 * Date: 2016-07-20
 * Time: 16:34
 * Description:
 */
public class ThreeAct extends AppCompatActivity{
    public static void startThreeAct(Activity activity) {
        activity.startActivity(new Intent(activity, ThreeAct.class));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act);
    }

    public void toResult(View view) {
        RxBus.getInstance().post("测试结果");
        finish();
    }
}
