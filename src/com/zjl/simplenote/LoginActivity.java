package com.zjl.simplenote;

import com.zjl.simplenote.db.DataOperate;
import com.zjl.simplenote.untl.PublicTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 上午10:50:44
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class LoginActivity extends Activity implements OnClickListener {

	private EditText accountText;

	private EditText passEditText;

	private Button loginButton;

	private Button registButton;

	private Checkable isrememberCheckable;

	private SharedPreferences pre;

	private SharedPreferences.Editor editor;

	private DataOperate dbop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dbop = new DataOperate(LoginActivity.this);
		setContentView(R.layout.login_layout);

		// 初始化控件信息
		accountText = (EditText) findViewById(R.id.account_edit);
		passEditText = (EditText) findViewById(R.id.pass_edit);
		isrememberCheckable = (Checkable) findViewById(R.id.remember_checkbox);
		loginButton = (Button) findViewById(R.id.login_button);
		registButton = (Button) findViewById(R.id.regist_button);

		// 存储数据
		pre = getSharedPreferences("userdata", MODE_PRIVATE);
		editor = pre.edit();

		loginButton.setOnClickListener(this);
		registButton.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.about:
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle("关于这个小程序");
			dialog.setMessage("媳妇儿出门了，我就写代码呗" + "\r\n" + "时间:2015-06-07"
					+ "\r\n" + "Author:oeaker@163.com");
			dialog.setPositiveButton("好的知道了",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});

			// dialog.setNegativeButton("再瞅瞅", null);
			dialog.show();
			break;

		default:
			break;
		}

		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		String userString = accountText.getText().toString();
		String pasString = passEditText.getText().toString();
		switch (v.getId()) {
		case R.id.login_button:
			// 登录的操作
			if ("".equals(userString) || "".equals(pasString)) {
				PublicTools.ShowMsg(this, "请填写完整后再试！！");
				return;
			}

			if (dbop.GetPassword(userString).equals(pasString)) {
				// 密码验证通过
				if (isrememberCheckable.isChecked()) {
					// 选中了保存密码
					editor.putString("user", userString);
					editor.putString("pass", pasString);
					editor.putBoolean("isremember", true);
					editor.commit();
				}

				// 跳转至标题展示页面
				Intent intent = new Intent(LoginActivity.this,
						DisplayTitleActicity.class);
				intent.putExtra("user", userString); // 把用户名传过去
				startActivity(intent);
			} else {
				Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT)
						.show();
				editor.putBoolean("isremember", false);
			}

			break;
		case R.id.regist_button:
			// 跳转至注册界面
			Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
