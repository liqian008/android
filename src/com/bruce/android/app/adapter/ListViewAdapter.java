package com.bruce.android.app.adapter;

import java.util.List;

import com.bruce.android.R;
import com.bruce.android.app.bean.RowItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private Context context;
	private List<RowItem> itemList;

	static class ItemHolder { // �Զ���ؼ�����
		public TextView title;
		public TextView author;
		public TextView date;
		public TextView count;
		public ImageView flag;
	}

	public ListViewAdapter(Context context, List<RowItem> dataList) { 
		this.context = context;
		this.itemList = dataList;
	}

	@Override
	public int getCount() {
		if (itemList != null) {
			return itemList.size();
		}
		return -1;
	}

	@Override
	public Object getItem(int position) {
		if (itemList != null) {
			return itemList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ItemHolder itemHolder = null;
		if (convertView == null) {
			// ����view
			convertView = View.inflate(context, R.layout.main_listitem, null);
			itemHolder = new ItemHolder();
			
			//��ȡ�ؼ�����
			itemHolder.title = (TextView)convertView.findViewById(R.id.news_listitem_title);
			itemHolder.author = (TextView)convertView.findViewById(R.id.news_listitem_author);
			itemHolder.count= (TextView)convertView.findViewById(R.id.news_listitem_commentCount);
			itemHolder.date= (TextView)convertView.findViewById(R.id.news_listitem_date);
			
			//���ÿؼ�����convertView
			convertView.setTag(itemHolder);
		} else {
			// ����view
			itemHolder = (ItemHolder) convertView.getTag();
		}
		
		RowItem item = itemList.get(position);
		
		//�������ֺ�ͼƬ
		itemHolder.title.setTag(item);//�������ز���(ʵ����) 
		itemHolder.title.setText(item.getTitle());
		itemHolder.author.setText(item.getAuthor());
		itemHolder.date.setText(item.getDate());
		itemHolder.count.setText(item.getCount()+"");
		
		return convertView;
	}

}
