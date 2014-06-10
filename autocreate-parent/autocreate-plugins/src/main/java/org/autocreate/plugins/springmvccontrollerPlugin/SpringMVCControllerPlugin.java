package org.autocreate.plugins.springmvccontrollerPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.autocreate.plugins.utils.TemplateFileLoader;
import org.gf.plugin.impl.AbstractSimpleCreatePlugin;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author gf
 * @date 2014-6-10
 * @description 生成SpringMVC Controller的JAVA文件
 */
public class SpringMVCControllerPlugin extends AbstractSimpleCreatePlugin {
	
	private static final String SUFFIX = ".java";

	public <T> void execute(Class<T> clazz, String outputPath) {
		Template template = TemplateFileLoader
				.loadTemplate(SpringMVCControllerPlugin.class.getSimpleName());
		Map<String, String> root = new HashMap<String, String>();
		root.put("beanName", clazz.getName());
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(new URI(outputPath+SUFFIX)));
			template.process(root, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("模板文件未找到", e);
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new RuntimeException("模板生成错误", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO错误", e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException("IO错误", e);
		}finally{
			if(out != null){
				out.close();
			}
		}
	}

}
