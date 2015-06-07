package com.zjl.simplenote;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.zjl.simplenote.adapter.TitleAdapter;
import com.zjl.simplenote.dao.MyNote;
import com.zjl.simplenote.db.DataOperate;
import com.zjl.simplenote.untl.PublicTools;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author zhengjialin:
 * @date ����ʱ�䣺2015-6-7 ����11:13:55
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class DisplayTitleActicity extends Activity implements
		OnItemClickListener, OnClickListener {

	private List<MyNote> noteList = new ArrayList<MyNote>();

	private ListView listView;

	private TitleAdapter adapter;

	private String user;

	private DataOperate dbop;

	private Button addnoteButton;

	private Button notifybButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.title_layout);

		dbop = new DataOperate(DisplayTitleActicity.this);
		user = getIntent().getStringExtra("user");
		if (!"".equals(user)) {
			LoadData();
		} else {
			Log.e(PublicTools.TAG, "�������ҳ���û���Ϊ��");
			return;
		}

		addnoteButton = (Button) findViewById(R.id.addnote);
		addnoteButton.setOnClickListener(this);

		notifybButton = (Button) findViewById(R.id.notify);
		notifybButton.setOnClickListener(this);

		listView = (ListView) findViewById(R.id.title_listview);
		adapter = new TitleAdapter(this, R.layout.title_list_layout, noteList);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);
	}

	/**
	 * �����ݿ��м�������
	 */
	private void LoadData() {

		Intent intent = null;
		if ("".equals(user)) {
			// user Ϊ�ձ�ʾδ��¼����ת������¼ҳ��
			intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		} else {
			noteList = dbop.GetNoteByUser(user);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		MyNote myNote = noteList.get(position);
		Intent intent = new Intent(this, NoteDetailActivity.class);

		// ������Ҫ���л����� model�ͱ���Ҫ���ɿ����л��Ľӿ�
		intent.putExtra("note", JSON.toJSONString(myNote));

		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addnote:
			Intent intent = null;
			if ("".equals(user)) {
				// user Ϊ�ձ�ʾδ��¼����ת������¼ҳ��
				Toast.makeText(this, "���¼�����������", Toast.LENGTH_SHORT).show();
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			} else {
				intent = new Intent(this, AddNoteActivity.class);
				intent.putExtra("user", user);
			}

			Log.d(PublicTools.TAG, "��д�µ��ռ�");
			startActivity(intent);
			break;
		case R.id.notify:

			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			@SuppressWarnings("deprecation")
			Notification notify = new Notification(R.drawable.ic_launcher,
					"this is a notify", System.currentTimeMillis());

			intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:15386638167"));
			PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
					PendingIntent.FLAG_CANCEL_CURRENT);

			notify.setLatestEventInfo(this, "����", "�������������", pi);

			Uri soundUri = Uri.fromFile(new File(
					"/system/media/audio/ringtones/ Basic_tone.ogg"));
			notify.sound = soundUri;

			long[] vibrates = { 0, 1000, 1000, 1000 };
			notify.vibrate = vibrates;

			notify.ledARGB = Color.GREEN;
			notify.ledOnMS = 1000;
			notify.ledOffMS = 1000;

			// notify.defaults = Notification.DEFAULT_ALL;

			manager.notify(1, notify);
			break;
		default:
			break;
		}

	}
}
