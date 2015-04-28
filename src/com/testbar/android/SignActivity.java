package com.testbar.android;

import com.testbar.android.util.LoginCommit;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import android.R.string;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends Activity implements OnClickListener{

	private EditText Name,Password,PasswordAgain;
	private Button commit;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_bag);
		setActionBarLayout(R.layout.sign_up_item);
		Bmob.initialize(SignActivity.this, "267ef22e7f2a44fee01869204b25da3f");
		commit = (Button) findViewById(R.id.button1);
		Name = (EditText) findViewById(R.id.editText1);
		Password = (EditText) findViewById(R.id.editText2);
		PasswordAgain = (EditText) findViewById(R.id.editText3);
		
		commit.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String mName = Name.getText().toString();
		String mPassword = Password.getText().toString();
		String mPasswordAgain = PasswordAgain.getText().toString();
		if(v.getId() == R.id.button1){
			if (!mPassword.equals(mPasswordAgain)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("请注意");
				builder.setMessage("您输入的两次密码不正确");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				builder.show();
			}else if (mName.equals("") || mPassword.equals("") || mPasswordAgain.equals("")) {
				Toast.makeText(SignActivity.this, "请填入您的信息", Toast.LENGTH_SHORT).show();
			}else {
				LoginCommit signCommit = new LoginCommit();
				signCommit.setName(mName);
				signCommit.setPassword(mPassword);
				signCommit.save(SignActivity.this, new SaveListener() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						Toast.makeText(SignActivity.this, "提交成功！", Toast.LENGTH_SHORT).show();
						finish();
					}
					
					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(SignActivity.this, "提交失败！", Toast.LENGTH_SHORT).show();
					}
				});
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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

}
