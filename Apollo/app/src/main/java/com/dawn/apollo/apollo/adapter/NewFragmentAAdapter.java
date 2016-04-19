package com.dawn.apollo.apollo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dawn.apollo.apollo.R;
import com.dawn.apollo.apollo.datamodel.NewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawn-pc on 2016/4/18.
 */
public class NewFragmentAAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<NewInfo> newInfoList;
    private LayoutInflater mInflater;

    private static final int TEXT_VIEW = 1;
    private static final int IMAG_VIEW = 2;
    private static final int FOOT_VIEW = 3;

    public NewFragmentAAdapter(Context context, List<NewInfo> mNewList){
        this.mContext = context;
        this.newInfoList = mNewList;
        mInflater = LayoutInflater.from(this.mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TEXT_VIEW) {
            final View view = mInflater.inflate(R.layout.adapter_news_text, parent, false);
            return new TextViewHolder(view);
        } else if (viewType == IMAG_VIEW) {
            View view = mInflater.inflate(R.layout.adapter_news_picture, parent, false);
            return new ImageViewHolder(view);
        }else if (viewType == FOOT_VIEW) {
            View view = mInflater.inflate(R.layout.adapter_load_more, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TextViewHolder){

        }else if(holder instanceof ImageViewHolder){

        }else if(holder instanceof FootViewHolder){
            //上拉加载更多布局数据绑定
        }
    }

    @Override
    public int getItemCount() {
        return newInfoList == null ? 1:newInfoList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position+1 == getItemCount()){
            return FOOT_VIEW;
        }else{
            if(newInfoList.get(position).getType().equals(TEXT_VIEW)){
                return TEXT_VIEW;
            }else{
                return IMAG_VIEW;
            }
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_content;
        public TextViewHolder(View itemView) {
            super(itemView);
            iv_image=(ImageView)itemView.findViewById(R.id.adapter_news_text_image);
            tv_title=(TextView)itemView.findViewById(R.id.adapter_news_text_title);
            tv_content=(TextView)itemView.findViewById(R.id.adapter_news_text_content);
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private ImageView iv_imageOne;
        private ImageView iv_imageTwo;
        private ImageView iv_imageThree;

        public ImageViewHolder(View itemView) {
            super(itemView);
            tv_title=(TextView)itemView.findViewById(R.id.adapter_news_text_title);
            iv_imageOne=(ImageView)itemView.findViewById(R.id.adapter_news_image_one);
            iv_imageTwo=(ImageView)itemView.findViewById(R.id.adapter_news_image_two);
            iv_imageThree=(ImageView)itemView.findViewById(R.id.adapter_news_image_three);
        }
    }

    public static class FootViewHolder extends  RecyclerView.ViewHolder{
        private TextView foot_view_item_tv;
        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv=(TextView)view.findViewById(R.id.foot_view_item_tv);
        }
    }
    /**
     * 进行下拉刷新数据添加 并且刷新UI
     * @param pInstanceBeans
     */
    public void addRefreshBeans(List<NewInfo> pInstanceBeans){
        List<NewInfo> temp=new ArrayList<NewInfo>();
        temp.addAll(pInstanceBeans);
//        temp.addAll(mAdvanceInstanceBeans);
//        mAdvanceInstanceBeans.removeAll(mAdvanceInstanceBeans);
//        mAdvanceInstanceBeans.addAll(temp);
        notifyDataSetChanged();
    }

    /**
     * 进行上拉加载更多 并且刷新UI
     * @param pInstanceBeans
     */
    public void addMoreBeans(List<NewInfo> pInstanceBeans){
//        mAdvanceInstanceBeans.addAll(pInstanceBeans);
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(Object o);
    }
    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
