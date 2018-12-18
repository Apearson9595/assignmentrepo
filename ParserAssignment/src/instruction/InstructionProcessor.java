package instruction;

import java.io.IOException;

import http.PostResponse;

/**
 * class to lookup valid instruction and process
 *
 */
public class InstructionProcessor {
	
/**
 * calls the valid instruction and performs it using the parameters
 * valid instructions are add, multiply and concat
 * @param instruction - the instruction to be carried out
 * @param param1 - first parameter to carry out instruction on
 * @param param2 - second parameter to carry out instruction on
 * @return - returns the result of the instruction
 * @throws IOException - when its not a valid instruction (i.e not add,multiply or concat)
 */
public String process(String instruction, String param1, String param2) throws IOException{
	
	String answer = null;
	Instruction taskInstruction = null;
	if (instruction.equals("add")) {
		taskInstruction = new Add();
	} else if (instruction.equals("multiply")) {
		taskInstruction = new Multiply();
	} else if (instruction.equals("concat")) {
		taskInstruction = new Concat();
	} else {
		throw new IOException("not a valid instruction");
	}
	answer = taskInstruction.doInstruction(param1, param2);
	
	return answer;

}
}
