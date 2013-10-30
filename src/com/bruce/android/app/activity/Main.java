package com.bruce.android.app.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bruce.android.R;
import com.bruce.android.app.AppContext;
import com.bruce.android.app.AppException;
import com.bruce.android.app.adapter.ListViewAdapter;
import com.bruce.android.app.bean.RowItem;
import com.bruce.android.app.net.ApiClient;
import com.bruce.android.app.utils.UIHelper;
import com.bruce.android.app.view.PullToRefreshListView;
import com.bruce.android.app.view.PullToRefreshListView.OnRefreshListener;
import com.bruce.android.app.view.ScrollLayout;

public class Main extends BaseActivity {

	private static final String TAG = "Main";

	private ScrollLayout mScrollLayout;
	private RadioButton[] mButtons;
	private int mViewCount;
	protected int mCurSel;

	private PullToRefreshListView jokesLv;
	private List<RowItem> jokesData = new ArrayList<RowItem>();

	
	private static final String nhxhJsonUrl = "http://www.toutiao.com/api/essay/recent/recent?tag=joke&count=20";

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0: {
				break;
			}
			case 1: {
				List<RowItem> itemList = (List<RowItem>) msg.obj;
				jokesData.clear();
				jokesData.addAll(itemList);
				ListViewAdapter listAdapter = new ListViewAdapter(Main.this,
						itemList);
				jokesLv.setAdapter(listAdapter);
				jokesLv.onRefreshComplete();
				break;
			}
			default: {
				break;
			}
			}
		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initViews();
		initPageScroll();
		initData();
	}

	private void initViews() {
		jokesLv = (PullToRefreshListView) findViewById(R.id.frame_listview_news);
		jokesLv.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				Log.v(TAG, "onRefresh");
				loadNewsList(handler);
			}
		});
		
		jokesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 点击头部、底部栏无效
				if (position == 0)
					return;
				TextView itemTitleView = (TextView) view.findViewById(R.id.news_listitem_title);
				RowItem rowItem = (RowItem) itemTitleView.getTag();
//				Log.v(TAG, "rowItem: "+rowItem);
				// 跳转到新闻详情
				UIHelper.showJokeDetail(view.getContext(), rowItem);
			}
		});
	}

	/**
	 * 初始化水平滚动翻页
	 */
	private void initPageScroll() {
		mScrollLayout = (ScrollLayout) findViewById(R.id.main_scroll_layout);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_footer);
		mViewCount = mScrollLayout.getChildCount();
		mButtons = new RadioButton[mViewCount];

		for (int i = 0; i < mViewCount; i++) {
			mButtons[i] = (RadioButton) linearLayout.getChildAt(i * 1);
			mButtons[i].setTag(i);
			mButtons[i].setChecked(false);
			mButtons[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					int pos = (Integer) (v.getTag());
					Log.v(TAG, "pos: " + pos);
					Log.v(TAG, "mCurSel: " + mCurSel);
					mScrollLayout.snapToScreen(pos);
				}
			});
		}

		// 设置第一显示屏
		mCurSel = 0;
		mButtons[mCurSel].setChecked(true);

		mScrollLayout
				.setOnViewChangeListener(new ScrollLayout.OnViewChangeListener() {
					public void onViewChange(int viewIndex) {
						Log.v(TAG, "viewChange!");
						setCurPoint(viewIndex);
						// 切换列表视图-如果列表数据为空：加载数据
						if(jokesData.isEmpty()){
							jokesLv.clickRefresh();
						}
					}
				});
	}

	private void initData() {
		if (jokesData.isEmpty()) {
			loadNewsList(handler);
		}
	}

	/**
	 * 设置底部栏当前焦点
	 * 
	 * @param index
	 */
	private void setCurPoint(int index) {
		Log.v(TAG, "index: " + index);
		if (index < 0 || index > mViewCount - 1 || mCurSel == index)
			return;

		mButtons[mCurSel].setChecked(false);
		mButtons[index].setChecked(true);
		mCurSel = index;
	}

	/**
	 * 监听返回--是否退出程序
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean flag = true;
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 是否退出应用
			UIHelper.Exit(this);
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {

		} else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			// 展示搜索页
			UIHelper.showSearch(Main.this);
		} else {
			flag = super.onKeyDown(keyCode, event);
		}
		return flag;
	}

	/**
	 * 加载列表数据
	 */
	private void loadNewsList(final Handler handler) {

		new Thread(new Runnable() {
			

			@Override
			public void run() {
				try {

					String response = ApiClient.http_get_string(
							(AppContext) getApplication(),
							nhxhJsonUrl);
//					Log.v(TAG, "response: " + response);
					List<RowItem> itemList = null;
					try {
						JSONObject dataJson = new JSONObject(response);
						JSONArray jokeArray = dataJson.getJSONArray("data");
//						Log.v(TAG, "jokeArray: " + jokeArray);
						
						if(jokeArray!=null&&jokeArray.length()>0){
							itemList = new ArrayList<RowItem>();
							for(int i=0;i<jokeArray.length();i++){
								JSONObject joke = jokeArray.getJSONObject(i);
								String title = joke.getString("text");
								String author = joke.getString("tag_name");
								String date = joke.getString("datetime");
								int count = joke.getInt("bury_count");
								RowItem item = new RowItem(title, author, date, count);
								itemList.add(item);
							}
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
					Message message = handler.obtainMessage();
					message.what = 1;
					message.obj = itemList;
					message.sendToTarget();
				} catch (AppException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

}
