package com.bruce.android.app.activity;

import com.bruce.android.R;
import com.bruce.android.app.bean.RowItem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDetail extends BaseActivity {

	private TextView titleView;
	private TextView contentView;
	private TextView descView;

	private String text;
	private String author;
	private String date;
	private int count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		//预加载数据
		preloadDate();
		initView();
	}

	@SuppressLint("NewApi")
	private void preloadDate() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		text = bundle.getString("text", "title");
		author = bundle.getString("author", "author");
		date = bundle.getString("date", "just");
		count = bundle.getInt("count", 0);
	}

	private void initView() {
		titleView = (TextView) findViewById(R.id.detail_title);
		contentView = (TextView) findViewById(R.id.detail_content);
		descView = (TextView) findViewById(R.id.detail_description);

		titleView.setText(text.substring(0, 10)+"...");
		contentView.setText(text);
		descView.setText("由"+author+"发表于"+date);
	}

}
