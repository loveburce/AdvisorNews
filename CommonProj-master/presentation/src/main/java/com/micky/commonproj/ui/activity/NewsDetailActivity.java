package com.micky.commonproj.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.micky.commonlib.config.Url;
import com.micky.commonlib.http.HttpUtil;
import com.micky.commonlib.http.json.NewDetailJson;
import com.micky.commonlib.http.model.NewDetailModle;
import com.micky.commonlib.http.model.NewModle;
import com.micky.commonlib.utils.StringUtils;
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
            newUrl = Url.NewDetail + newID + Url.endDetailUrl;
            loadData(newUrl);
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

    private void loadData(String url) {
        if (HttpUtil.isNetworkAvailable(NewsDetailActivity.this)) {
            loadNewDetailData(url);
        } else {
//            dismissProgressDialog();
//            showShortToast(getString(R.string.not_network));
//            String result = getCacheStr(newUrl);
//            if (!StringUtils.isEmpty(result)) {
//                getResult(result);
//            }
        }
    }

    public void loadNewDetailData(String url) {
        String result;
        try {
            result = HttpUtil.getByHttpClient(this, url);
            getResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void getResult(String result) {
        newDetailModle = NewDetailJson.instance(this).readJsonNewModles(result, newID);
        if (newDetailModle == null)
            return;
//        setCacheStr(newUrl, result);
        if (!"".equals(newDetailModle.getUrl_mp4())) {
//            imageLoader.displayImage(newDetailModle.getCover(), newImg, options, this, this);
            newImg.setVisibility(View.VISIBLE);
        } else {
            if (newDetailModle.getImgList().size() > 0) {
                imgCountString = "共" + newDetailModle.getImgList().size() + "张";
//                imageLoader.displayImage(newDetailModle.getImgList().get(0), newImg, options, this, this);
                newImg.setVisibility(View.VISIBLE);
            }
        }
        newTitle.setText(newDetailModle.getTitle());
        newTime.setText("来源：" + newDetailModle.getSource() + " " + newDetailModle.getPtime());
        String content = newDetailModle.getBody();
        content = content.replace("<!--VIDEO#1--></p><p>", "");
        content = content.replace("<!--VIDEO#2--></p><p>", "");
        content = content.replace("<!--VIDEO#3--></p><p>", "");
        content = content.replace("<!--VIDEO#4--></p><p>", "");
        content = content.replace("<!--REWARD#0--></p><p>", "");
        webView.setHtmlFromString(content, false);
//        dismissProgressDialog();
        // webView.loadDataWithBaseURL(null, content, "text/html", "utf-8",
        // null);
    }

    @OnClick(R.id.new_img)
    public void imageMore(View view) {
        try {
            Bundle bundle = new Bundle();
            bundle.putSerializable("newDetailModle", newDetailModle);
            if (!"".equals(newDetailModle.getUrl_mp4())) {
                bundle.putString("playUrl", newDetailModle.getUrl_mp4());
//                openActivity(VideoPlayActivity_.class, bundle, 0);
            } else {
//                openActivity(ImageDetailActivity_.class, bundle, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            // progressBar.setVisibility(View.GONE);
//            dismissProgressDialog();
            webView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // progressBar.setVisibility(View.GONE);
//            dismissProgressDialog();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }

}
