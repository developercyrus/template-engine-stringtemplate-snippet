package template.engine.stringtemplate.a2;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.stringtemplate.v4.ST;

public class StringTemplateBuilder {	
	private static StringTemplateBuilder instance = new StringTemplateBuilder();
	private ST st = null;
	
	private StringTemplateBuilder() {		
	}
	
	public static StringTemplateBuilder getInstance(){
		return instance;
	}
	
	public StringTemplateBuilder config(String template) {		
		try {
			st =  new ST(FileUtils.readFileToString(new File(StringTemplateBuilder.class.getResource(template).getFile()), "utf-8"), '$', '$');
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return this;
	}
	
	
	public StringTemplateBuilder add(String attribute, String value) {
		st.add(attribute, value);
		return this;
	}
	
	public StringTemplateBuilder add(String attribute, int value) {
		st.add(attribute, value);
		return this;
	}

	public StringTemplateBuilder add(String attribute, List<?> list) {
		st.add(attribute, list);			
		return this;
	}
	
	public StringTemplateBuilder add(String attribute, Map<?, ?> map) {
		st.add(attribute, map);			
		return this;
	}
	
	public String build() {
		return st.render();
	}
}
