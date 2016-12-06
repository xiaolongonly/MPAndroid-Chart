package cn.xiaolongonly.mpchartsample.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.xiaolongonly.mpchartsample.R;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/6-15:51
 */
public abstract class BaseTitleActivity extends BaseActivity {
    private String defaultTitle = "";
    protected TextView tvTitle;
    protected ImageView ivBack;

    @Override
    public void setContentView(View view) {
        getExtra();
        initTitleBar();
        super.setContentView(view);
    }

    private void initTitleBar() {
        tvTitle = findView(R.id.tvTitle);
        tvTitle.setText(defaultTitle);
        ivBack = findView(R.id.title_left);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getExtra() {
        defaultTitle = getIntent().getStringExtra("title");
    }

    public static Bundle BuildBundle(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        return bundle;
    }
}
