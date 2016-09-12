package com.qf.sep12bantangexam;

import android.widget.RadioGroup;

import com.qf.flag.CommunityFragment;
import com.qf.flag.HomeFragment;
import com.qf.flag.MessageFragment;
import com.qf.flag.PersonalFragment;
import com.qf.flag.PublishFragment;
import com.qf.utillibary.base.BaseActivity;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        radioGroup = findViewByIds(R.id.rg_tab);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.getChildAt(0).performClick();
    }



    /**
     * RadioGroup的监听方法
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                //点击"首页"
                fragmentManager(R.id.fl_fragment, new HomeFragment(), "home");
                break;
            case R.id.rb_discover:
                //点击"community"
                fragmentManager(R.id.fl_fragment, new CommunityFragment(), "community");
                break;
            case R.id.rb_publish:
                //点击"publish"
                fragmentManager(R.id.fl_fragment, new PublishFragment(), "Publish");
                break;
            case R.id.rb_msg:
                //点击"消息"
                fragmentManager(R.id.fl_fragment, new MessageFragment(), "msg");
                break;
            case R.id.rb_personal:
                //点击"个人"
                fragmentManager(R.id.fl_fragment, new PersonalFragment(), "personal");
                break;
        }
    }
}
