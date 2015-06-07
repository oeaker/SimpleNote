package com.zjl.simplenote.untl;

import android.content.Context;
import android.widget.Toast;

import com.zjl.simplenote.db.MyDBHelper;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 上午10:48:28
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class PublicTools {

	/**
	 * 日志标记
	 */
	public static final String TAG = "zjl";

	/**
	 * 获取数据库操作helper
	 * 
	 * @param context
	 *            当前上下文
	 * @return 数据库操作helper
	 */
	public static MyDBHelper getDBHelper(Context context) {
		return new MyDBHelper(context, "Note。db", null, 1);
	}

	/**
	 * 提示信息
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            提示信息
	 */
	public static void ShowMsg(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
