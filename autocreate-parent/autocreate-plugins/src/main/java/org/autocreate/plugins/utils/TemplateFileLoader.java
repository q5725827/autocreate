package org.autocreate.plugins.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author gf
 * @date 2014-6-10
 * @description 获取template文件的类
 */
public class TemplateFileLoader {

	/**
	 * 获取FreeMarker文件的基础路径
	 */
	private static String baseTemplatePath;

	private final static String FTLSUFFIX = ".ftl";

	private static Configuration cfg = new Configuration();

	static {
		try {
			// 默认从类路径下加载
			baseTemplatePath = RelativePath.getFullPathRelateClass("../../../../",TemplateFileLoader.class);
			cfg.setDirectoryForTemplateLoading(new File(baseTemplatePath));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("读入模板库错误", e);
		}
		// 设置基础的数据包装
		cfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	/**
	 * @author gf
	 * @date 2014-6-10
	 * @description 从指定的模板路径下获取模板文件
	 * @param name
	 *            模板文件名
	 */
	public static Template loadTemplate(String name) {
		Template template = null;
		try {
			template = cfg.getTemplate(name + FTLSUFFIX);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("模板读取错误" + name, e);
		}
		return template;
	}

}
