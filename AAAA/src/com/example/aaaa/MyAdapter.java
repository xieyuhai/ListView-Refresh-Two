package com.example.aaaa;

import java.util.List;

import com.example.entity.Bean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private List<Bean> list;
	private LayoutInflater inflater;
	public ArrayMap<Integer, Boolean> isChecked;

	public MyAdapter(Context context, List<Bean> list) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
		init();
	}

	@SuppressLint("NewApi")
	private void init() {
		isChecked = new ArrayMap<Integer, Boolean>();
		for (int i = 0; i < list.size(); i++) {
			isChecked.put(i, false);
		}
	}

	@SuppressLint("NewApi")
	public void put(Integer key, Boolean value) {
		isChecked.put(key, value);
	}

	@SuppressLint("NewApi")
	public boolean get(Integer key) {
		return isChecked.get(key);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Bean getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			// convertView=inflater.inflate(R.layout.test, parent,false);
			convertView = new TextView(context);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		/**
		 * 自定义监听在activity中实现刷新
		 */
		// holder.tv.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// if (click != null) {
		// click.onclick(holder.tv, position);
		// }
		// }
		// });
		Bean item = getItem(position);
		if (get(position)) {
			holder.tv.setText(item + "刷新" + position);
			holder.tv.setBackgroundColor(Color.WHITE);
		} else {
			holder.tv.setText(item.name);
			holder.tv.setBackgroundColor(Color.GRAY);
		}
		return convertView;
	}

	public void update(ListView listView, int position) {
		if (listView != null) {
			int start = listView.getFirstVisiblePosition();
			int last = listView.getLastVisiblePosition();

			for (int i = start; i <= last; i++)
				if (/* position==i *//* or */getItem(position) == listView.getItemAtPosition(i)) {
					View view = listView.getChildAt(i - start);
					getView(i, view, listView);
					break;
				}
		}
	}

	private static class ViewHolder {
		TextView tv;
	}

	public onViewClick click;

	public void setOnViewClick(onViewClick click) {
		this.click = click;
	}

	public interface onViewClick {
		void onclick(View convertView, int index);
	}
}
