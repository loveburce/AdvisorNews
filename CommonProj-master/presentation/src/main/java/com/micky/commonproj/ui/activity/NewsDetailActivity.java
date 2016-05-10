package com.micky.commonproj.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.micky.commonlib.config.Url;
import com.micky.commonlib.http.model.NewDetailModle;
import com.micky.commonlib.http.model.NewModle;
import com.micky.commonproj.R;
import com.micky.commonproj.ui.customview.htmltextview.HtmlTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dawn-pc on 2016/5/9.
 */
public class NewsDetailActivity extends BaseActivity{
    @Bind(R.id.back)
    TextView tv_back;
    @Bind(R.id.title)
    TextView tv_title;

    @Bind(R.id.new_title)
    protected TextView newTitle;
    @Bind(R.id.new_time)
    protected TextView newTime;
    @Bind(R.id.wb_details)
    protected HtmlTextView webView;
    // @ViewById(R.id.progressBar)
    // protected ProgressBar progressBar;
//    @Bind(R.id.progressPieView)
//    protected ProgressPieView mProgressPieView;
    @Bind(R.id.new_img)
    protected ImageView newImg;
    @Bind(R.id.img_count)
    protected TextView imgCount;
    @Bind(R.id.play)
    protected ImageView mPlay;
    private String newUrl;
    private NewModle newModle;
    private String newID;

    private String imgCountString;
    private NewDetailModle newDetailModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail, true);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData(){
        try {
            newModle = (NewModle) getIntent().getExtras().getSerializable("newModle");
            newID = newModle.getDocid();
            newUrl = getUrl(newID);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView(){
        tv_title.setText("新闻详情");
    }

    @OnClick(R.id.back)
    public void submit() {
        // TODO submit data to server...
        finish();
    }

    public String getUrl(String newId) {
        return Url.NewDetail + newId + Url.endDetailUrl;
    }

    
}
