package com.zjl.simplenote;

import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;
import com.zjl.simplenote.dao.MyNote;
import com.zjl.simplenote.untl.PublicTools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 下午12:21:51
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class NoteDetailActivity extends Activity {

	private MyNote note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notedetail_layout);

		String user = getIntent().getStringExtra("user");
		if (!"".equals(user)) {
			String noteJson = getIntent().getStringExtra("note");
			if ("".equals(noteJson)) {
				return;
			}

			note = JSON.parseObject(noteJson, MyNote.class);

			TextView titleTextView = (TextView) findViewById(R.id.note_title);
			TextView addtimeTextView = (TextView) findViewById(R.id.note_addtime);
			TextView userTextView = (TextView) findViewById(R.id.note_user);
			TextView contentTextView = (TextView) findViewById(R.id.note_content);

			titleTextView.setText(note.getTitle());
			addtimeTextView.setText("Time：" + note.getAddtime());
			userTextView.setText("Author：" + note.getUser());
			contentTextView.setText(note.getContent());

			View detailView = (View) findViewById(R.id.visibility_layout);
			detailView.setVisibility(View.VISIBLE);

		} else {
			PublicTools.ShowMsg(this, "程序出现异常");
		}
	}
}
