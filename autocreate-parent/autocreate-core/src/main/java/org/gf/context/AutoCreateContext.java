package org.gf.context;

import java.util.List;
import java.util.Map;

import org.gf.plugin.AutoCreatePlugin;

/**
 * @author gf
 * @date 2014-6-10
 * @description 
 *  自动生成的上下文容器
 */
public interface AutoCreateContext {

	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 执行生成
	 */
	public void execute();
	
	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 添加代码生成插件
	 * @param plugin 需要执行的生成代码插件
	 */
	public void addPlugin(AutoCreatePlugin plugin);
	
	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 删除代码生成插件
	 * @param plugin 需要删除的生成代码插件
	 */
	public void removePlugin(AutoCreatePlugin plugin);
	
	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 获取所有的生成代码插件
	 */
	public List<AutoCreatePlugin> getPlugins();
	
	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 重新设置的生成代码插件
	 * @param plugins 重新设置的生成代码插件
	 */
	public void setPlugins(List<AutoCreatePlugin> plugins);
	
	public void setConfig(Map<String, String> map);
	
	public Map<String, String> getConfig();
	
}
