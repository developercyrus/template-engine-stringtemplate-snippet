package template.engine.stringtemplate.a1;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.custommonkey.xmlunit.Diff;
import org.junit.Test;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.xml.sax.SAXException;

import template.engine.stringtemplate.a1.Person;

public class StringTemplateBuilderTest {	
	private static Logger logger = Logger.getLogger(StringTemplateBuilderTest.class);

	@Test
    public void testIfGivenList() throws IOException, SAXException {			
		// use $ as delimiter
		STGroup stGroup = new STGroupFile("template/engine/stringtemplate/a1/if.sql.stg", '$', '$');
		ST st = stGroup.getInstanceOf("if");
		st.add("DEPT_ID", "cars");
		String actual = st.render();		
		logger.debug(actual);
		
		STGroup stGroup2 = new STGroupFile("template/engine/stringtemplate/a1/if.expected.given.stg", '$', '$');
		ST st2 = stGroup2.getInstanceOf("if");
		String expected = st2.render();
		logger.debug(expected);
					
        assertEquals(expected, actual);
    }
	
	@Test
    public void testIfWithoutGivenList() throws IOException, SAXException {			
		// use $ as delimiter
		STGroup stGroup = new STGroupFile("template/engine/stringtemplate/a1/if.sql.stg", '$', '$');
		ST st = stGroup.getInstanceOf("if");
		String actual = st.render();		
		logger.debug(actual);
		
		STGroup stGroup2 = new STGroupFile("template/engine/stringtemplate/a1/if.expected.without.given.stg", '$', '$');
		ST st2 = stGroup2.getInstanceOf("if");
		String expected = st2.render();
		logger.debug(expected);
					
        assertEquals(expected, actual);
    }
	
	@Test
    public void testList() throws IOException, SAXException {
		List<Person> persons = new ArrayList<Person>();		
		persons.add(new Person(1, "Peter"));
		persons.add(new Person(2, "Sam"));
		persons.add(new Person(3, "Mary"));
				
		// use $ as delimiter
		STGroup stGroup = new STGroupFile("template/engine/stringtemplate/a1/loop.html.stg", '$', '$');
		ST st = stGroup.getInstanceOf("loop");
		st.add("persons", persons);
		String actual = st.render();		
		logger.debug(actual);
		
		STGroup stGroup2 = new STGroupFile("template/engine/stringtemplate/a1/loop.expected.stg", '$', '$');
		ST st2 = stGroup2.getInstanceOf("loop");
		String expected = st2.render();
		logger.debug(expected);
				
		Diff diff = new Diff(expected, actual);  
        assertTrue(diff.similar());
        assertXMLEqual(expected, actual);
        assertEquals(expected, actual);
    }
	
	@Test
    public void testMap() throws IOException, SAXException {
		List<String> a = Arrays.asList("1", "2", "3");
		List<String> b = Arrays.asList("1", "2");
		List<String> c = Arrays.asList("1");
		Map<String, List<String>> x = new HashMap<String, List<String>>();
		x.put("a", a);
		x.put("b", b);
		x.put("c", c);
				
		// use $ as delimiter
		STGroup stGroup = new STGroupFile("template/engine/stringtemplate/a1/map.html.stg", '$', '$');
		ST st = stGroup.getInstanceOf("map");
		st.add("x", x);
		String actual = st.render();		
		logger.debug(actual);
		
		STGroup stGroup2 = new STGroupFile("template/engine/stringtemplate/a1/map.expected.stg", '$', '$');
		ST st2 = stGroup2.getInstanceOf("map");
		String expected = st2.render();
		logger.debug(expected);
				
        assertEquals(expected, actual);
    }
}
