package com.testbar.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.testbar.android.adapter.MyAdapter;
import com.testbar.android.util.ZoomOutPageTransformer;
import com.testbar.android.view.PointIndicator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix.ScaleToFit;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	private boolean isOpen = false;
	private DrawerLayout mDrawer;

	private ListView listView;
	// private ArrayAdapter<String> arrayAdapter;
	private int[] res;
	private ViewPager viewpager;
	private List<ImageView> listImages = new ArrayList<ImageView>();
	private LinearLayout linearlayout;
	private PointIndicator indicator;
	private RelativeLayout login;
	private int currentItem;

	private ListView listView_menu;
	private SimpleAdapter simpleAdapter;
	private List<Map<String, Object>> list_map;
	private int res_menu[];
	private String[] str;
	private LinearLayout menu_left;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setActionBarLayout(R.layout.item);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		menu_left = (LinearLayout) findViewById(R.id.left_drawer);
		// listView = (ListView) findViewById(R.id.left_drawer);
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		// indicator = (PointIndicator) findViewById(R.id.linearlayout1);
		// 对抽屉中的列表菜单进行适配
		listView_menu = (ListView) findViewById(R.id.listview_menu);
		list_map = new ArrayList<Map<String, Object>>();
		res_menu = new int[] { R.drawable.home, R.drawable.management,
				R.drawable.collect, R.drawable.message, R.drawable.setting };
		str = new String[] { "首页", "主题日报", "我的收藏", "消息", "设置" };
		simpleAdapter = new SimpleAdapter(MainActivity.this, getData(),
				R.layout.item_menu, new String[] { "image", "content" },
				new int[] { R.id.imgbtn_menu, R.id.textview_menu });
		listView_menu.setAdapter(simpleAdapter);

		viewpager.setPageTransformer(true, new ZoomOutPageTransformer());

		res = new int[] { R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
				R.drawable.img_4, R.drawable.img_5 };
		// arrayAdapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_expandable_list_item_1, str);
		// listView.setAdapter(arrayAdapter);
		for (int i = 0; i < res.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(res[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			listImages.add(imageView);
		}

		MyAdapter adapter = new MyAdapter(res, listImages);
		viewpager.setAdapter(adapter);
		// indicator.setViewPage(viewpager);

		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				currentItem = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		login = (RelativeLayout) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						loginActivity.class);
				startActivity(intent);
			}
		});


	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", res_menu[i]);
			map.put("content", str[i]);
			list_map.add(map);
		}
		return list_map;
	}

	protected void onStart() {
		super.onStart();
		ScheduledExecutorService scheduledExecutorService = Executors
				.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 5,
				5, TimeUnit.SECONDS);
	}

	private class ViewPagerTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			currentItem = (currentItem + 1) % res.length;
			handler.obtainMessage().sendToTarget();
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewpager.setCurrentItem(currentItem);
		};
	};

	protected void onStop() {
		super.onStop();
	};

	public void onClick(View view) {
		ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton1);
		float x = imageButton.getTranslationX();
		if (!isOpen) {
			ObjectAnimator.ofFloat(imageButton, "translationX", x, x - 15)
					.setDuration(300).start();
			mDrawer.openDrawer(Gravity.LEFT);
			isOpen = true;
		} else {
			float x1 = imageButton.getTranslationX();
			ObjectAnimator.ofFloat(imageButton, "translationX", x1, x1 + 15)
					.setDuration(300).start();
			mDrawer.closeDrawer(Gravity.LEFT);
			isOpen = false;
		}

	}

	public void setActionBarLayout(int layoutId) {
		ActionBar actionBar = getActionBar();
		if (null != actionBar) {
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowCustomEnabled(true);

			LayoutInflater inflator = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflator.inflate(layoutId, null);
			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			actionBar.setCustomView(v, layout);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(this, "正在进行刷新，请稍后...", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
