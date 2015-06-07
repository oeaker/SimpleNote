package com.zjl.simplenote.adapter;

import java.util.List;

import com.zjl.simplenote.R;
import com.zjl.simplenote.dao.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 上午11:44:57
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class TitleAdapter extends ArrayAdapter<MyNote> {

	private int rescourceID;

	public TitleAdapter(Context context, int resource, List<MyNote> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.rescourceID = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = new ViewHolder();
		if (null == convertView) {
			convertView = LayoutInflater.from(getContext()).inflate(
					rescourceID, null);
			viewHolder.time = (TextView) convertView.findViewById(R.id.addtime);
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		MyNote myNote = getItem(position);
		viewHolder.time.setText(myNote.getAddtime());
		viewHolder.title.setText(myNote.getTitle());

		return convertView;
	}

	class ViewHolder {
		TextView time;
		TextView title;
	}
}
