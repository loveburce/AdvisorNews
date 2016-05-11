package com.micky.commonproj.ui.customview.DragImageView;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.micky.commonproj.R;

/**
 * 自定义适配器
 * @author wulianghuan
 *
 */
public class MyPagerAdapter extends PagerAdapter {
	Vibrator vibrator;
	ArrayList<ImageInfo> data;
	Activity activity;
	LayoutParams params;

	public MyPagerAdapter(Activity activity, ArrayList<ImageInfo> data) {
		this.activity = activity;
		this.data = data;
		vibrator = (Vibrator) activity
				.getSystemService(Context.VIBRATOR_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int index) {
		Log.v("test", index + "index");

		View view = activity.getLayoutInflater().inflate(R.layout.grid, null);
		GridView gridView = (GridView) view.findViewById(R.id.gridView1);
		gridView.setNumColumns(2);
		gridView.setVerticalSpacing(5);
		gridView.setHorizontalSpacing(5);
		gridView.setAdapter(new BaseAdapter() {

			@Override
			public int getCount() {
				return 8;
			}

			@Override
			public Object getItem(int position) {
				return position;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View item = LayoutInflater.from(activity).inflate(
						R.layout.grid_item, null);
				ImageView iv = (ImageView) item.findViewById(R.id.imageView1);
				RelativeLayout relativeLayout = (RelativeLayout)item.findViewById(R.id.relativeLayout);
				iv.setImageResource((data.get(index * 8 + position)).imageId);
				relativeLayout.setBackgroundResource((data.get(index * 8 + position)).bgId);
				relativeLayout.getBackground().setAlpha(225);
				TextView tv = (TextView) item.findViewById(R.id.msg);
				tv.setText((data.get(index * 8 + position)).imageMsg);
				return item;
			}
		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				View view = arg1;
				arg1.setVisibility(View.INVISIBLE);

				params = new WindowManager.LayoutParams();
			//	activity.getWindowManager().addView(view, params);
				vibrator.vibrate(2500);
				return true;
			}
		});
		// gridView.setOnTouchListener(new View.OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View v, MotionEvent event) {
		//
		// return true;
		// }
		// });
		((ViewPager) container).addView(view);

		return view;
	}
}
