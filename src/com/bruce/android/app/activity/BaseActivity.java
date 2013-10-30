package com.bruce.android.app.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bruce.android.app.AppManager;

public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AppManager.getInstance().addActivity(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		AppManager.getInstance().finishActivity(this);
		
	}
}
