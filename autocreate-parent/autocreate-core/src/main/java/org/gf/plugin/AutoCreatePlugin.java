package org.gf.plugin;

/**
 * @author gf
 * @date 2014-6-10
 * @description 
 * 插件接口
 */
public interface AutoCreatePlugin {

	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description
	 * 判断当前类是否需要插件生成代码  
	 * @return true 当前插件需要根据传入类生成代码
	 * @return false 当前插件不生成代码
	 */
	public <T> boolean isfixable(Class<T> clazz);
	
	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description
	 * 判断当前类是否完成代码生成 是否需要继续向下生成传递
	 * @return true 此类完成代码生成
	 * @return false 此类还需继续代码生成
	 */
	public <T> boolean isFinish(Class<T> clazz);

	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 执行当前代码生成插件
	 */
	public <T> void execute(Class<T> clazz,String outputPath);
	
}
