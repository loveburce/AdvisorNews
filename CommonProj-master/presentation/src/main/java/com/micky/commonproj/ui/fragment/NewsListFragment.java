package com.micky.commonproj.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.micky.commonlib.http.model.NewModle;
import com.micky.commonproj.R;
import com.micky.commonproj.domain.db.DBCore;
import com.micky.commonproj.domain.db.dao.DaoSession;
import com.micky.commonproj.domain.model.ChannelItem;
import com.micky.commonproj.presenter.NewsListPresenter;
import com.micky.commonproj.presenter.NewsListView;
import com.micky.commonproj.presenter.impl.NewsListPresenterImpl;
import com.micky.commonproj.ui.activity.BaseActivity;
import com.micky.commonproj.ui.activity.NewsDetailActivity;
import com.micky.commonproj.ui.adapter.NewsListAdapter;
import com.micky.commonproj.ui.view.ItemDecoration;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dawn-pc on 2016/5/5.
 */
public class NewsListFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, NewsListView {
    View rootView;
    NewsListPresenter newsPresenter;

    @Bind(R.id.fragment_news_slider)
    SliderLayout sliderLayout;
    @Bind(R.id.fragment_news_pager_indicator)
    PagerIndicator pagerIndicator;
    @Bind(R.id.fragment_news_recycler)
    RecyclerView recyclerViewNews;
    @Bind(R.id.fragment_news_swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    NewsListAdapter newsListAdapter;
    List<NewModle> newModles;
    protected HashMap<String, String> url_maps;
    protected HashMap<String, NewModle> newHashMap;
    private int index = 0;
    private boolean isRefresh = false;
    ChannelItem channelItem;
    String typeTg;
    long idKey;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news_list,null);
        ButterKnife.bind(this, rootView);

        Bundle bundle=getArguments();
        if(bundle!=null){
            typeTg = bundle.getString("extra");
            idKey = bundle.getLong("IdKey");
        }

        DaoSession daoSession = DBCore.getDaoSession();
        channelItem = daoSession.getChannelItemDao().load(idKey);
        Log.d("test", "StateChanged =url " + typeTg);
        newsPresenter = new NewsListPresenterImpl(this);

        String url = getUrlByParameter(typeTg,index+"");
        String urls = channelItem.getUrlHead()+channelItem.getUrlKey()+"/"+index+channelItem.getUrlEnd();

        Log.d("test", "StateChanged =url " + url);
        Log.d("test", "StateChanged =urls " + urls);
        newsPresenter.getNewsData(urls,channelItem.getUrlKey());

        initView();
        initData();

        return rootView;
    }

    private void initData(){
        url_maps = new HashMap<String, String>();
        newHashMap = new HashMap<String, NewModle>();
    }

    private void initView() {

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefresh) {
                    url_maps.clear();
                    newHashMap.clear();

                    newsListAdapter.notifyItemRemoved(newsListAdapter.getItemCount());
                    isRefresh = true;
                    index = 0;
                    String url = channelItem.getUrlHead()+channelItem.getUrlKey()+"/"+index+channelItem.getUrlEnd();
                    Log.d("test", "StateChanged =url " + url);
                    newsPresenter.getNewsData(url,channelItem.getUrlKey());

                }
            }
        });

        recyclerViewNews.setHasFixedSize(true);
        recyclerViewNews.addItemDecoration(new ItemDecoration(getActivity()));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewNews.setLayoutManager(linearLayoutManager);
        newsListAdapter = new NewsListAdapter();
        recyclerViewNews.setAdapter(newsListAdapter);

        recyclerViewNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == newsListAdapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        newsListAdapter.notifyItemRemoved(newsListAdapter.getItemCount());
                        return;
                    }

                    if (!isRefresh) {
                        isRefresh = true;
                        index += 20;
//                        String url = getUrlByParameter(typeTg, index + "");
                        String url = channelItem.getUrlHead() + channelItem.getUrlKey() + "/" + index + channelItem.getUrlEnd();
                        Log.d("test", "StateChanged =url " + url);
                        newsPresenter.getNewsData(url, channelItem.getUrlKey());
                    }
                }
            }
        });

        //添加点击事件
        newsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
                NewModle newModle = newModles.get(position);
                enterDetailActivity(newModle);

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public void enterDetailActivity(NewModle newModle) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("newModle", newModle);
        Class<?> class1;
        if (newModle.getImagesModle() != null && newModle.getImagesModle().getImgList().size() > 1) {
            class1 = NewsDetailActivity.class;
        } else {
            class1 = NewsDetailActivity.class;
        }


//        ((BaseActivity) getActivity()).startActivity(class1, bundle, 0);
         Intent intent = new Intent(getActivity(), class1);
         intent.putExtras(bundle);
        ((BaseActivity) getActivity()).startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setupNewsData(List<NewModle> newModles) {
        swipeRefreshLayout.setRefreshing(false);
        isRefresh = false;
        this.newModles = newModles;
        Log.d("placeList","placeListplaceList: "+newModles.size());
        newsListAdapter.setData(newModles);
        newsListAdapter.notifyDataSetChanged();


        if (!isNullString(newModles.get(0).getImgsrc()))
            newHashMap.put(newModles.get(0).getImgsrc(), newModles.get(0));
        if (!isNullString(newModles.get(1).getImgsrc()))
            newHashMap.put(newModles.get(1).getImgsrc(), newModles.get(1));
        if (!isNullString(newModles.get(2).getImgsrc()))
            newHashMap.put(newModles.get(2).getImgsrc(), newModles.get(2));
        if (!isNullString(newModles.get(3).getImgsrc()))
            newHashMap.put(newModles.get(3).getImgsrc(), newModles.get(3));

        if (!isNullString(newModles.get(0).getImgsrc()))
            url_maps.put(newModles.get(0).getTitle(), newModles.get(0).getImgsrc());
        if (!isNullString(newModles.get(1).getImgsrc()))
            url_maps.put(newModles.get(1).getTitle(), newModles.get(1).getImgsrc());
        if (!isNullString(newModles.get(2).getImgsrc()))
            url_maps.put(newModles.get(2).getTitle(), newModles.get(2).getImgsrc());
        if (!isNullString(newModles.get(3).getImgsrc()))
            url_maps.put(newModles.get(3).getTitle(), newModles.get(3).getImgsrc());

        Log.d("textSliderView","textSliderView : "+url_maps.size());
        sliderLayout.removeAllSliders();
        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);


            sliderLayout.addSlider(textSliderView);
        }
//
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.addOnPageChangeListener(this);

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
