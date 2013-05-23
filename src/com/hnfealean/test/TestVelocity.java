package com.hnfealean.test;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class TestVelocity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List l = new ArrayList();
		//l.add("hello");
		//l.contains("hello");
		try {
			Properties prop = new Properties();
			// prop.put("runtime.log",
			// config.getServletContext().getRealPath("/WEB-INF/log/velocity.log"));
			prop.put("file.resource.loader.path", "C:\\vm");
			prop.put("input.encoding", "UTF-8");
			prop.put("output.encoding", "UTF-8");
			Velocity.init("src/velocity.properties");
			VelocityContext context = new VelocityContext();
			Template template = Velocity.getTemplate("helloworld.vm");
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			sw.flush();
			System.out.println(sw.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
