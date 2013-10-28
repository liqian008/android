package com.bruce.android.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.bruce.android.R;
import com.bruce.android.app.AppManager;
import com.bruce.android.app.activity.Main;

/**
 * Ӧ�ó���UI���߰�����װUI��ص�һЩ����
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class UIHelper {

	public final static int LISTVIEW_ACTION_INIT = 0x01;
	public final static int LISTVIEW_ACTION_REFRESH = 0x02;
	public final static int LISTVIEW_ACTION_SCROLL = 0x03;
	public final static int LISTVIEW_ACTION_CHANGE_CATALOG = 0x04;

	public final static int LISTVIEW_DATA_MORE = 0x01;
	public final static int LISTVIEW_DATA_LOADING = 0x02;
	public final static int LISTVIEW_DATA_FULL = 0x03;
	public final static int LISTVIEW_DATA_EMPTY = 0x04;

	public final static int LISTVIEW_DATATYPE_NEWS = 0x01;
	public final static int LISTVIEW_DATATYPE_BLOG = 0x02;
	public final static int LISTVIEW_DATATYPE_POST = 0x03;
	public final static int LISTVIEW_DATATYPE_TWEET = 0x04;
	public final static int LISTVIEW_DATATYPE_ACTIVE = 0x05;
	public final static int LISTVIEW_DATATYPE_MESSAGE = 0x06;
	public final static int LISTVIEW_DATATYPE_COMMENT = 0x07;

	public final static int REQUEST_CODE_FOR_RESULT = 0x01;
	public final static int REQUEST_CODE_FOR_REPLY = 0x02;


	/**
	 * ��ʾ��ҳ
	 * 
	 * @param activity
	 */
	public static void showHome(Activity activity) {
		Intent intent = new Intent(activity, Main.class);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * ��ʾͼƬ�Ի���
	 * 
	 * @param context
	 * @param imgUrl
	 */
	public static void showImageDialog(Context context, String imgUrl) {
//		Intent intent = new Intent(context, ImageDialog.class);
//		intent.putExtra("img_url", imgUrl);
//		context.startActivity(intent);
	}

	public static void showImageZoomDialog(Context context, String imgUrl) {
//		Intent intent = new Intent(context, ImageZoomDialog.class);
//		intent.putExtra("img_url", imgUrl);
//		context.startActivity(intent);
	}

	/**
	 * ��ʾϵͳ���ý���
	 * 
	 * @param context
	 */
	public static void showSetting(Context context) {
//		Intent intent = new Intent(context, Setting.class);
//		context.startActivity(intent);
	}

	/**
	 * ��ʾ��������
	 * 
	 * @param context
	 */
	public static void showSearch(Context context) {
//		Intent intent = new Intent(context, Search.class);
//		context.startActivity(intent);
	}



	/**
	 * �������
	 * 
	 * @param context
	 * @param url
	 */
	public static void openBrowser(Context context, String url) {
		try {
			Uri uri = Uri.parse(url);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			context.startActivity(it);
		} catch (Exception e) {
			e.printStackTrace();
			toastMessage(context, "�޷��������ҳ", 500);
		}
	}



	/**
	 * ����Toast��Ϣ
	 * 
	 * @param msg
	 */
	public static void toastMessage(Context cont, String msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void toastMessage(Context cont, int msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void toastMessage(Context cont, String msg, int time) {
		Toast.makeText(cont, msg, time).show();
	}

	/**
	 * ������ؼ����¼�
	 * 
	 * @param activity
	 * @return
	 */
	public static View.OnClickListener finish(final Activity activity) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				activity.finish();
			}
		};
	}

	/**
	 * ��ʾ��������
	 * 
	 * @param context
	 */
	public static void showAbout(Context context) {
//		Intent intent = new Intent(context, About.class);
//		context.startActivity(intent);
	}

	/**
	 * ��ʾ�û�����
	 * 
	 * @param context
	 */
	public static void showFeedBack(Context context) {
//		Intent intent = new Intent(context, FeedBack.class);
//		context.startActivity(intent);
	}




	/**
	 * �˳�����
	 * 
	 * @param cont
	 */
	public static void Exit(final Context cont) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_menu_surelogout); 
		builder.setPositiveButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// �˳�
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.cancle,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}


}
