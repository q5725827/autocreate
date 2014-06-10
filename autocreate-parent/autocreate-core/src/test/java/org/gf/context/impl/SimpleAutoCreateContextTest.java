package org.gf.context.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SimpleAutoCreateContextTest {

	private SimpleAutoCreateContext context = new SimpleAutoCreateContext();

	@Test
	public void testGetClassSet() {
		String scanPackage = "org.gf.plugin";
		List<String> list = new ArrayList<String>();
		list.add(scanPackage);
		List<Class<?>> classList = context.getClassFromBasePackage(list);
		System.out.println(classList);
	}

}
