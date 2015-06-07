package com.zjl.simplenote;

import com.zjl.simplenote.db.DataOperate;
import com.zjl.simplenote.untl.PublicTools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author zhengjialin:
 * @date ����ʱ�䣺2015-6-7 ����12:46:03
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class AddNoteActivity extends Activity {

	private DataOperate dbop;

	private EditText titleEditText;

	private EditText contentEditText;

	private Button commitButton;

	private String user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnote_layout);
		Log.d(PublicTools.TAG, "��������д��־������");
		user = getIntent().getStringExtra("user");
		if ("".equals(user)) {
			PublicTools.ShowMsg(this, "���½�����");
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);

		} else {
			titleEditText = (EditText) findViewById(R.id.title_addText);
			contentEditText = (EditText) findViewById(R.id.content_addText);
			commitButton = (Button) findViewById(R.id.commit_btn);

			commitButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					String title = titleEditText.getText().toString();
					String content = contentEditText.getText().toString();
					if ("".equals(title) || "".equals(content)) {
						PublicTools.ShowMsg(AddNoteActivity.this, "����д�������ύ");
						return;
					}

					// TODO Auto-generated method stub
					dbop = new DataOperate(AddNoteActivity.this);
					if (dbop.AddNote(title, content, user)) {
						PublicTools.ShowMsg(AddNoteActivity.this, "��ӳɹ�");
						Intent intent = new Intent(AddNoteActivity.this,
								DisplayTitleActicity.class);
						intent.putExtra("user", user);
						startActivity(intent);
					} else {
						PublicTools.ShowMsg(AddNoteActivity.this, "���ʧ��");
					}
				}
			});
		}
	}
}
