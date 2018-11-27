package Instruction;

public class Multiply {

	public Multiply () {
	}
	public String domultiply(String params) {
		
		String[] splitString = params.split(",");
		String param1 = splitString [0];
		String param2 = splitString [1];
		int i = Integer.parseInt(param1);
		int n = Integer.parseInt(param2);
		int result = i * n;
		return Integer.toString(result);
		

		
	}
}
