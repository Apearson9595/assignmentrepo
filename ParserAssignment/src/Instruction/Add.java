package Instruction;

public class Add {

	public Add () {
	}
	public String doadd(String params) {
		
		String[] splitString = params.split(",");
		String param1 = splitString [0];
		String param2 = splitString [1];
		int i = Integer.parseInt(param1);
		int n = Integer.parseInt(param2);
		int result = i + n;
		return Integer.toString(result);
		

		
	}
}
