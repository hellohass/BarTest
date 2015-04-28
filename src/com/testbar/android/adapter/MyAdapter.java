package com.testbar.android.adapter;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MyAdapter extends PagerAdapter {

	private int[] res;
	private List<ImageView> viewlist;
	public MyAdapter(int[] res,List<ImageView> viewlist) {
		// TODO Auto-generated constructor stub
		this.res = res;
		this.viewlist = viewlist;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(viewlist.get(position));
		return viewlist.get(position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(viewlist.get(position));
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return res.length;
	}
	

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

}
