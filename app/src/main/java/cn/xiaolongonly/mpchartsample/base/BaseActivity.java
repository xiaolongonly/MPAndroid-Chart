package cn.xiaolongonly.mpchartsample.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/6-9:13
 */
public abstract class BaseActivity extends AppCompatActivity {
    private View contentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(contentView);
        initView();
        setListener();
    }



    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void setListener();

    protected <V extends View> V findView(int resId) {
        return (V) contentView.findViewById(resId);
    }


}
