package com.zjl.simplenote.dao;

import java.io.Serializable;

/**
 * @author zhengjialin:
 * @date 创建时间：2015-6-7 上午11:34:48
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class MyNote implements Serializable {

	/**
	 * 序列化对象id
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
