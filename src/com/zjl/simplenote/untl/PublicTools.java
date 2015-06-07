package com.zjl.simplenote.untl;

import android.content.Context;
import android.widget.Toast;

import com.zjl.simplenote.db.MyDBHelper;

/**
 * @author zhengjialin:
 * @date ����ʱ�䣺2015-6-7 ����10:48:28
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class PublicTools {

	/**
	 * ��־���
	 */
	public static final String TAG = "zjl";

	/**
	 * ��ȡ���ݿ����helper
	 * 
	 * @param context
	 *            ��ǰ������
	 * @return ���ݿ����helper
	 */
	public static MyDBHelper getDBHelper(Context context) {
		return new MyDBHelper(context, "Note��db", null, 1);
	}

	/**
	 * ��ʾ��Ϣ
	 * 
	 * @param context
	 *            ������
	 * @param message
	 *            ��ʾ��Ϣ
	 */
	public static void ShowMsg(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
