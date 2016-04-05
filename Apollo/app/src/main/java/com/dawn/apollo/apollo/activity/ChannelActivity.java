package com.dawn.apollo.apollo.activity;

import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;

import com.dawn.apollo.apollo.R;
import com.dawn.apollo.apollo.customview.DragView.DragAdapter;
import com.dawn.apollo.apollo.customview.DragView.DragGrid;
import com.dawn.apollo.apollo.customview.DragView.OtherAdapter;
import com.dawn.apollo.apollo.customview.DragView.OtherGridView;
import com.dawn.apollo.apollo.datamodel.ChannelItem;

import java.util.ArrayList;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/4/5.
 */
public class ChannelActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static String TAG = "ChannelActivity";
    /** 用户栏目的GRIDVIEW */
//    @InjectView(R.id.title)
    private DragGrid userGridView;
    /** 其它栏目的GRIDVIEW */
    private OtherGridView otherGridView;
    /** 用户栏目对应的适配器，可以拖动 */
    DragAdapter userAdapter;
    /** 其它栏目对应的适配器 */
    OtherAdapter otherAdapter;
    /** 其它栏目列表 */
    ArrayList<ChannelItem> otherChannelList = new ArrayList<ChannelItem>();
    /** 用户栏目列表 */
    ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    /** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
    boolean isMove = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }


}
