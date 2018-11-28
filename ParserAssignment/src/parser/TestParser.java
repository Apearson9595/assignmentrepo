package parser;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class TestParser {
	@Test
	public void testParser() {
		String json = "{id:s113867}";
		Parser parser = new Parser();
		Map<String,String> map = parser.parse (json);
		assertEquals("{id=s113867}", map.toString());
		System.out.println(map.toString());
	}
	
	//"id:s195206,tasks:task1"
	
	@Test
	public void testParser2() {
		String json = "{id:s195206,tasks:task1}";
		Parser parser = new Parser();
		Map<String,String> map = parser.parse (json);
		System.out.println(map.toString());
		assertEquals("{id=s195206, tasks=task1}", map.toString());
	}

}
