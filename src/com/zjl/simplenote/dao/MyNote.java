package com.zjl.simplenote.dao;

import java.io.Serializable;

/**
 * @author zhengjialin:
 * @date ����ʱ�䣺2015-6-7 ����11:34:48
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class MyNote implements Serializable {

	/**
	 * ���л�����id
	 */
	private static final long serialVersionUID = 3323544525820494200L;
	private String title;
	private String content;
	private String user;
	private String addtime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}
