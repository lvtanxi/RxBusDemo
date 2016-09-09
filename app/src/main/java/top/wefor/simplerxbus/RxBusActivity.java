package top.wefor.simplerxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import top.wefor.simplerxbus.rxbus.RxBus;

/**
 * Created on 16/7/19.
 *
 * @author ice
 */
public class RxBusActivity extends AppCompatActivity {

    Button mYesBtn,mNoBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);

        mYesBtn = (Button) findViewById(R.id.yes_btn);
        mNoBtn = (Button) findViewById(R.id.no_btn);

        mYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getInstance().post(mYesBtn.getText().toString());
                finish();
            }
        });

        mNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getInstance().post(mNoBtn.getText().toString());
                finish();
            }
        });
        doSubscribe();
    }

    public void wode(View view) {
        ThreeAct.startThreeAct(this);

    }

    private void doSubscribe() {
        Subscription subscription1 = RxBus.getInstance()
                .toObserverable(String.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(RxBusActivity.this, "事件内容：" + s, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                    }
                });


        RxBus.getInstance().addSubscription(this, subscription1);
    }

    @Override
    protected void onDestroy() {
        RxBus.getInstance().unSubscribe(this);
        super.onDestroy();
    }
}
