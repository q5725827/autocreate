package com.rock.createfile;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.autocreate.plugins.springmvccontrollerPlugin.SpringMVCControllerPlugin;
import org.gf.context.AutoCreateContext;
import org.gf.context.impl.SimpleAutoCreateContext;
import org.gf.plugin.AutoCreatePlugin;

import com.rock.gf.Test;

public class CreateFileMain {
	
	
	public static void main(String[] args) throws URISyntaxException {
		
		AutoCreateContext context = new SimpleAutoCreateContext();
		
		AutoCreatePlugin springMVCplugin = new SpringMVCControllerPlugin();
		
		Map<String,String> config = new HashMap<String,String>();
		config.put("output", CreateFileMain.class.getResource("/").toString());
		config.put("input","com.rock.gf");
		context.setConfig(config);
		context.addPlugin(springMVCplugin);
		
		context.execute();

	}
}
