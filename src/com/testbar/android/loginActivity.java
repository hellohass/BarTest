package com.testbar.android;

import java.util.List;

import com.testbar.android.util.LoginCommit;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends Activity implements OnClickListener {
	
	private ImageButton loginimg1;
	private EditText userName,Password;
	private TextView textView1;
	private Button login_btn1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		setActionBarLayout(R.layout.login_bar_item);
		
		loginimg1 = (ImageButton) findViewById(R.id.login_imgbtn1);
		textView1 = (TextView) findViewById(R.id.textView1);
		userName = (EditText) findViewById(R.id.editName);
		Password = (EditText) findViewById(R.id.editPassword);
		login_btn1 = (Button) findViewById(R.id.login_btn1);
		
		textView1.setOnClickListener(this);
		loginimg1.setOnClickListener(this);
		Password.setOnClickListener(this);
		login_btn1.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_imgbtn1:
			Toast.makeText(loginActivity.this, "还没写，别着急。。。", 1000).show();
			break;

		case R.id.textView1:
			Intent intent = new Intent(loginActivity.this,SignActivity.class);
			startActivity(intent);
			break;
		
		case R.id.login_btn1:
			final String user_name = userName.getText().toString();
			if (user_name.equals("")) {
				AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
				builder.setMessage("用户名不能为空");
				builder.setCancelable(false);
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.create().show();
			}else {
				BmobQuery<LoginCommit> query = new BmobQuery<LoginCommit>();
				query.addWhereEqualTo("Name", user_name);
				query.findObjects(loginActivity.this,new FindListener<LoginCommit>() {
					
					@Override
					public void onSuccess(List<LoginCommit> loginCommits) {
						// TODO Auto-generated method stu
						String str = "";
						for (LoginCommit loginCommit : loginCommits) {
							str += loginCommit.getName();
						}
						if(str == ""){
							AlertDialog.Builder builder1 = new AlertDialog.Builder(loginActivity.this);
							builder1.setMessage("用户名不存在，请前往注册");
							builder1.setCancelable(false);
							builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Intent intent = new Intent(loginActivity.this,SignActivity.class);
									startActivity(intent);
								}
							});	
							builder1.create().show();
						}else if (str.equals(user_name)) {
							Toast.makeText(loginActivity.this, "用户存在", Toast.LENGTH_SHORT).show();
						}
					}
					
					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
					}
				} );
			}
			
		default:
			break;
		}
	}

	@SuppressLint("NewApi")
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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
}
