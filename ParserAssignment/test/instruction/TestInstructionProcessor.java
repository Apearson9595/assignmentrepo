package instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.Test;


public class TestInstructionProcessor {
	
	@Test
	public void testAdd() throws IOException {
		InstructionProcessor ip = new InstructionProcessor();
		String answer = ip.process("add", "33", "25");
		assertEquals("58", answer);
	}
	
	@Test
	public void testMultiply() throws IOException {
		InstructionProcessor ip = new InstructionProcessor();
		String answer = ip.process("multiply", "2", "100");
		assertEquals("200", answer);
	}
	@Test
	public void testConcat() throws IOException {
		InstructionProcessor ip = new InstructionProcessor();
		String answer = ip.process("concat", "111", "234");
		assertEquals("111234", answer);
	}
	@Test (expected = IOException.class)
	public void testSubtract() throws IOException {
		InstructionProcessor ip = new InstructionProcessor();
		ip.process("subtract", "111", "234");
		
	}

}
