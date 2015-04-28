package com.testbar.android.adapter;

import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

public class MySimpleAdapter extends BaseAdapter {

	Context context;
	private int[] count;
	public MySimpleAdapter(Context context,int[] count) {
		this.context = context;
		this.count = count;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
