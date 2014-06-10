package org.gf.plugin.impl;

import org.gf.plugin.AutoCreatePlugin;

/**
 * @author gf
 * @date 2014-6-10
 * @description
 * 插件抽象基类
 */
public abstract class AbstractSimpleCreatePlugin implements AutoCreatePlugin{

	/**
	 * 生成的代码放置的package
	 */
	private String packageName;
	
	/**
	 * 默认情况下继续传递生成代码逻辑
	 */
	public <T> boolean isFinish(Class<T> clazz) {
		return false;
	}
	
	/**
	 * 默认情况所有类都符合当前生成逻辑
	 */
	public <T> boolean isfixable(Class<T> clazz) {
		return true;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
