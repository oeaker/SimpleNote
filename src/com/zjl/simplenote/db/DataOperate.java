package com.zjl.simplenote.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zjl.simplenote.dao.MyNote;
import com.zjl.simplenote.untl.PublicTools;

import android.R.bool;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 下午1:01:16
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class DataOperate {

	private MyDBHelper dbHelper;

	private SQLiteDatabase db;

	/**
	 * 构造函数
	 * 
	 * @param context
	 *            上下文信息
	 */
	public DataOperate(Context context) {
		new PublicTools();
		dbHelper = PublicTools.getDBHelper(context);
	}

	@SuppressWarnings("deprecation")
	public boolean AddNote(String title, String content, String user) {
		db = dbHelper.getWritableDatabase();
		try {
			db.execSQL(
					"insert into notebook(title,content,user,addtime) values (?,?,?,?)",
					new String[] { title, content, user,
							new Date().toLocaleString() });
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 根据用户获取所有的日记信息
	 * 
	 * @param user
	 *            用户
	 * @return 日记信息
	 */
	public List<MyNote> GetNoteByUser(String user) {

		List<MyNote> myNotes = new ArrayList<MyNote>();
		db = dbHelper.getReadableDatabase();

		Cursor cursor = db.rawQuery("select * from notebook where user = ?",
				new String[] { user });
		if (cursor.moveToFirst()) {
			MyNote myNote = null;

			do {

				myNote = new MyNote();
				myNote.setTitle(cursor.getString(cursor.getColumnIndex("title")));
				myNote.setContent(cursor.getString(cursor
						.getColumnIndex("content")));
				myNote.setAddtime(cursor.getString(cursor
						.getColumnIndex("addtime")));
				myNote.setUser(cursor.getString(cursor.getColumnIndex("user")));

				myNotes.add(myNote);

			} while (cursor.moveToNext());
		}

		return myNotes;

	}

	/**
	 * 根据用户名获取密码信息
	 * 
	 * @param user
	 *            用户名
	 * @return 密码
	 */
	public String GetPassword(String user) {
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select pass from userinfo where user = ?",
				new String[] { user });

		if (cursor.moveToFirst()) {

			return cursor.getString(cursor.getColumnIndex("pass"));
		}

		return "";
	}

	/**
	 * 注册用户
	 * 
	 * @param user
	 *            用户名
	 * @param pass
	 *            密码
	 * @return
	 */
	public Boolean RegistUser(String user, String pass) {

		db = dbHelper.getWritableDatabase();

		try {
			db.execSQL("insert into userinfo(user,pass) values(?,?)",
					new String[] { user, pass });
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		return true;

	}
}
