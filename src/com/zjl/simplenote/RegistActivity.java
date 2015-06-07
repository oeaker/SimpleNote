package com.zjl.simplenote;

import com.zjl.simplenote.db.DataOperate;
import com.zjl.simplenote.untl.PublicTools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 上午11:14:27
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class RegistActivity extends Activity implements OnClickListener {

	private DataOperate dbop;

	private EditText accountText;

	private EditText passEditText;

	private Button registButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_layout);

		dbop = new DataOperate(this);

		accountText = (EditText) findViewById(R.id.account_edit);
		passEditText = (EditText) findViewById(R.id.pass_edit);
		registButton = (Button) findViewById(R.id.regist_button);

		// 注册事件
		registButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.regist_button:
			String user = accountText.getText().toString();
			String pass = passEditText.getText().toString();

			if ("".equals(user) || "".equals(pass)) {
				PublicTools.ShowMsg(this, "请填写完整后再试");
			} else {
				if (dbop.RegistUser(user, pass)) {
					// 跳转到标题页面
					Intent intent = new Intent(RegistActivity.this,
							DisplayTitleActicity.class);
					intent.putExtra("user", user);
					startActivity(intent);

				} else {
					PublicTools.ShowMsg(this, "注册失败");
				}
			}

			break;
		default:
			break;
		}
	}
}
