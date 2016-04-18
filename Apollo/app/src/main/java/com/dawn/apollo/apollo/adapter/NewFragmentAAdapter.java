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
public class NewFragmentAAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
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
            return new ItemViewHolder(view);
        } else if (viewType == IMAG_VIEW) {
            View view = mInflater.inflate(R.layout.adapter_news_picture, parent, false);
            return new FootViewHolder(view);
        }else if (viewType == FOOT_VIEW) {
//            View view = mInflater.inflate(R.layout.instance_load_more_layout, parent, false);
//            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if(holder instanceof ItemViewHolder){
//            AdvanceInstanceBean advanceInstanceBean=mAdvanceInstanceBeans.get(position);
//            if(advanceInstanceBean!=null){
//                final List<InstanceBean> instanceBeans=advanceInstanceBean.getInstanceBeans();
//                if(instanceBeans.size()==2){
//                    ((ItemViewHolder) holder).item_img_one.setImageResource(instanceBeans.get(0).getImg());
//                    ((ItemViewHolder)holder).item_tv_one.setText(instanceBeans.get(0).getTitle());
//                    ((ItemViewHolder) holder).item_img_two.setImageResource(instanceBeans.get(1).getImg());
//                    ((ItemViewHolder)holder).item_tv_two.setText(instanceBeans.get(1).getTitle());
//                    ((ItemViewHolder) holder).leftL.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (onItemClickListener != null) {
//                                onItemClickListener.onItemClick(instanceBeans.get(0));
//                            }
//                        }
//                    });
//                    ((ItemViewHolder) holder).rightL.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if(onItemClickListener!=null){
//                                onItemClickListener.onItemClick(instanceBeans.get(1));
//                            }
//                        }
//                    });
//                }else {
//                    ((ItemViewHolder) holder).item_img_one.setImageResource(instanceBeans.get(0).getImg());
//                    ((ItemViewHolder)holder).item_tv_one.setText(instanceBeans.get(0).getTitle());
//                    ((ItemViewHolder) holder).item_img_two.setImageResource(R.drawable.moren);
//                    ((ItemViewHolder)holder).item_tv_two.setText("");
//                }
//            }
//        }else if(holder instanceof FootViewHolder){
//            //上拉加载更多布局数据绑定
//        }
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
            return ITEM_VIEW;
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout leftL,rightL;
        private ImageView item_img_one;
        private TextView item_tv_one;
        private ImageView item_img_two;
        private TextView item_tv_two;
        public ItemViewHolder(View itemView) {
            super(itemView);
//            leftL=(LinearLayout)itemView.findViewById(R.id.leftL);
//            rightL=(LinearLayout)itemView.findViewById(R.id.rightL);
//            item_img_one=(ImageView)itemView.findViewById(R.id.item_img_one);
//            item_tv_one=(TextView)itemView.findViewById(R.id.item_tv_one);
//            item_img_two=(ImageView)itemView.findViewById(R.id.item_img_two);
//            item_tv_two=(TextView)itemView.findViewById(R.id.item_tv_two);
        }
    }
    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends  RecyclerView.ViewHolder{
        private TextView foot_view_item_tv;
        public FootViewHolder(View view) {
            super(view);
//            foot_view_item_tv=(TextView)view.findViewById(R.id.foot_view_item_tv);
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
}
