package org.gf.context.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gf.context.AutoCreateContext;
import org.gf.plugin.AutoCreatePlugin;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

/**
 * @author gf
 * @date 2014-6-10
 * @description 基础和默认的自动生成上下文
 */
public class SimpleAutoCreateContext implements AutoCreateContext {

	public SimpleAutoCreateContext() {
		pluginsList = new ArrayList<AutoCreatePlugin>();
		configMap = new HashMap<String, String>();
		configMap.put("output", SimpleAutoCreateContext.class.getResource("/")
				.toString());
	}

	/**
	 * 插件保存容器
	 */
	private List<AutoCreatePlugin> pluginsList;

	/**
	 * 配置参数
	 * */
	private Map<String, String> configMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.gf.context.AutoCreateContext#addPlugin(org.gf.plugin.AutoCreatePlugin
	 * )
	 */
	public void addPlugin(AutoCreatePlugin plugin) {
		pluginsList.add(plugin);
	}

	/*
	 * 执行生成逻辑
	 */
	public void execute() {
		if (pluginsList == null || pluginsList.size() == 0) {
			throw new RuntimeException("生成代码插件不能为空! ");
		}
		// 获取需要生成的类
		String inputPath = configMap.get("input");
		List<String> list = new ArrayList<String>();
		list.add(inputPath);
		List<Class<?>> tempClasses = getClassFromBasePackage(list);
		if (tempClasses == null) {
			return;
		}
		// 遍历生成代码插件
		for (Class<?> tempClass : tempClasses)
			for (AutoCreatePlugin plugin : pluginsList) {
				String outputPath = configMap.get("output")
						+ tempClass.getSimpleName();
				plugin.execute(tempClass, outputPath);
				// 当前插件阻止代码生成 跳出
				if (plugin.isFinish(tempClass)) {
					break;
				}
			}
	}

	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 返回获取的Class文件
	 */
	public List<Class<?>> getClassFromBasePackage(List<String> inputPath) {
		try {
			return getClassSet(inputPath);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO错误",e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 加载当前路径下的所有类
	 *//*
	private List<Class<?>> ergodic(File file) {
		List<Class<?>> resultFileName = new ArrayList<Class<?>>();
		File[] files = file.listFiles();
		if (files == null)
			// 判断目录下是不是空的
			return resultFileName;
		for (File f : files) {
			// 判断是否文件夹
			if (f.isDirectory()) {
				// 调用自身,查找子目录
				resultFileName.addAll(ergodic(f));
			} else {
				Class<?> clazz = getClass().getClassLoader().
			}
		}
		return resultFileName;
	}*/
	
	
	public List<Class<?>> getClassSet(List<String> packagesList) throws IOException, ClassNotFoundException {
//		this.classSet.clear();
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		List<Class<?>> resultFileName = new ArrayList<Class<?>>();
		if (!packagesList.isEmpty()) {
				for (String pkg : packagesList) {
					String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
							ClassUtils.convertClassNameToResourcePath(pkg) + "/**/*.class";
					Resource[] resources = resourcePatternResolver.getResources(pattern);
					MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
					for (Resource resource : resources) {
						if (resource.isReadable()) {
							MetadataReader reader = readerFactory.getMetadataReader(resource);
							String className = reader.getClassMetadata().getClassName();
							resultFileName.add(Class.forName(className));
						}
					}
				}
		}
		return resultFileName;
	}
	
	
	

	public Map<String, String> getConfig() {
		return configMap;
	}

	public List<AutoCreatePlugin> getPlugins() {
		return pluginsList;
	}

	public void removePlugin(AutoCreatePlugin plugin) {
		pluginsList.remove(plugin);
	}

	public void setConfig(Map<String, String> map) {
		this.configMap = map;
	}

	public void setPlugins(List<AutoCreatePlugin> plugins) {
		pluginsList = plugins;
	}

}
