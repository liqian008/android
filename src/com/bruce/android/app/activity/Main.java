package com.bruce.android.app.activity;

import com.bruce.android.R;
import com.bruce.android.app.utils.UIHelper;
import com.bruce.android.app.view.ScrollLayout;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class Main extends Activity {

	private static final String TAG = "Main";

	private ScrollLayout mScrollLayout;
	private RadioButton[] mButtons;
	private int mViewCount;
	protected int mCurSel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initPageScroll();
	}

	/**
	 * ��ʼ��ˮƽ������ҳ
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
					// �����ǰ��ˢ��
					// if (mCurSel == pos) {
					// switch (pos) {
					// case 0:// ��Ѷ+����
					// if (lvNews.getVisibility() == View.VISIBLE)
					// lvNews.clickRefresh();
					// else
					// lvBlog.clickRefresh();
					// break;
					// case 1:// �ʴ�
					// lvQuestion.clickRefresh();
					// break;
					// case 2:// ����
					// lvTweet.clickRefresh();
					// break;
					// case 3:// ��̬+����
					// if (lvActive.getVisibility() == View.VISIBLE)
					// lvActive.clickRefresh();
					// else
					// lvMsg.clickRefresh();
					// break;
					// }
					// }
					mScrollLayout.snapToScreen(pos);
				}
			});
		}

		// ���õ�һ��ʾ��
		mCurSel = 0;
		mButtons[mCurSel].setChecked(true);

		mScrollLayout
				.setOnViewChangeListener(new ScrollLayout.OnViewChangeListener() {
					public void OnViewChange(int viewIndex) {
						Log.v(TAG, "viewChange!");
						// �л��б���ͼ-����б�����Ϊ�գ���������
						setCurPoint(viewIndex);
					}
				});
	}

	/**
	 * ���õײ�����ǰ����
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
	 * ��������--�Ƿ��˳�����
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean flag = true;
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �Ƿ��˳�Ӧ��
			UIHelper.Exit(this);
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {

		} else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			// չʾ����ҳ
			UIHelper.showSearch(Main.this);
		} else {
			flag = super.onKeyDown(keyCode, event);
		}
		return flag;
	}

}
