package com.zjl.simplenote.db;

import com.zjl.simplenote.untl.PublicTools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 上午10:37:30
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class MyDBHelper extends SQLiteOpenHelper {

	private static String Create_NoteBook = "create table notebook("
			+ "id integer primary key autoincrement, title text,content text, user text,addtime text)";

	private static String Create_UserInfo = "create table userinfo("
			+ "id integer primary key autoincrement,user text,pass text)";

	private Context mContext;

	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(Create_NoteBook);
		db.execSQL(Create_UserInfo);
		Log.d(PublicTools.TAG, "创建数据表成功");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
