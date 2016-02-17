package map.karen.com.residemenu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * package : map.karen.com.materialdesignui.activity
 * describe: ${CLASS_NAME}
 * author  : Karen
 * date    : 2015/10/10
 * link    : 904869397@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        initView();
        initClickListener();
        initLogic();
    }

    abstract int initLayout();
    abstract void initView();
    abstract void initClickListener();
    abstract void initLogic();




}
