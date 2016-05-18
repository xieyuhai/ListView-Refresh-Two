package com.example.aaaa;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Bean;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity/* implements onViewClick */ {
	private MyAdapter adapter;
	private List<Bean> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		list = new ArrayList<Bean>();
		for (int i = 0; i < 100; i++) {
			Bean bean = new Bean();
			bean.id = i;
			bean.name = "张三";
			bean.age = 10 + "" + i;
			list.add(bean);
		}

		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, list);

		adapter = new MyAdapter(this, list);
		setListAdapter(adapter);
		/**
		 * 通过实现自定义监听器实现点击
		 */
		// adapter.setOnViewClick(new onViewClick() {
		// @Override
		// public void onclick(View convertView, int index) {
		// list.set(index, "jjkfsjks");
		// // list.add(index, "jjkfsjks");
		// adapter.notifyDataSetChanged();
		// }
		// });
	}

	/**
	 * 通过实现自定义监听器实现点击
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		adapter.put(position, !adapter.get(position));
		adapter.update(getListView(), position);

		Toast.makeText(this, position + "", Toast.LENGTH_LONG).show();
	}

	// @Override
	// public void onclick(View convertView, int index) {
	//
	// }
}
