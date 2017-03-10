package template.engine.stringtemplate.a2;

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


public class StringTemplateBuildTest {	
	private static Logger logger = Logger.getLogger(StringTemplateBuildTest.class);

	@Test
    public void testIfGivenList() throws IOException, SAXException {			
		String actual = StringTemplateBuilder
						.getInstance()
						.config("if.sql.stg")
						.add("DEPT_ID", "cars")
						.build();
		logger.debug(actual);
		
		String expected = StringTemplateBuilder
						.getInstance()
						.config("if.expected.given.stg")
						.build();		
		logger.debug(expected);
		
		assertEquals(expected, actual);
    }
	
	@Test
    public void testIfWithoutGivenList() throws IOException, SAXException {			
		String actual = StringTemplateBuilder
						.getInstance()
						.config("if.sql.stg")
						.build();
		logger.debug(actual);

		String expected = StringTemplateBuilder
						.getInstance()
						.config("if.expected.without.given.stg")
						.build();		
		logger.debug(expected);
		
		assertEquals(expected, actual);
    }
	
	@Test
    public void testList() throws IOException, SAXException {
		List<Person> persons = new ArrayList<Person>();		
		persons.add(new Person(1, "Peter"));
		persons.add(new Person(2, "Sam"));
		persons.add(new Person(3, "Mary"));
				
		String actual = StringTemplateBuilder
						.getInstance()
						.config("loop.html.stg")
						.add("persons", persons)
						.build();
		logger.debug(actual);
		
		String expected = StringTemplateBuilder
						.getInstance()
						.config("loop.expected.stg")
						.build();
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
				
		String actual = StringTemplateBuilder
						.getInstance()
						.config("map.html.stg")
						.add("x", x)
						.build();
		logger.debug(actual);
		
		String expected = StringTemplateBuilder
						.getInstance()
						.config("map.expected.stg")
						.build();
		logger.debug(expected);
				
        assertEquals(expected, actual);
    }
}
