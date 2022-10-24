import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum NumRim{I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);
	private int arab;
	NumRim(int arab) {
		this.arab = arab;
	}
	public int getNumArab() {
		return arab;
	}
};
public class Test {

	public static void main(String[] args) throws IOException, InputException {
		System.out.println("input:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		 //String pattern2 = "(^\\d\\s[-+*/]\\s\\d$)|(^(X|IX|IV|V?I{0,3})\\s[-+/*]\\s(X|IX|IV|V?I{0,3})$)";
		   /* Pattern p5 = Pattern.compile(pattern2);
		    Matcher m5 = p5.matcher(input);
		    if(!m.matches()) {
		    	throw new InputException("Что-то пошло не так");
		    }*/
		String q = calc(input);
		System.out.println("output:\n" + q);
	}
	public static String calc(String input) throws InputException {
		String result = null;
		int res, i0, i2, count = 0;
		String[] arr = input.split(" ");
		String[] pattern = {"^\\d$", "^X|IX|IV|V?I{0,3}$"};
		
		if(arr.length < 3)
			throw new InputException("a string is not a mathematical operation");
		if(arr.length > 3)
			throw new InputException("the format of the mathematical operation does not satisfy the task - two operands and one operator (+, -, /, *)");
		
		String i1 = arr[1];
		/*String[][] regex = {{"^\\d$", "^X|IX|IV|V?I{0,3}$"}, {arr[0], arr[2]}};
		for(int i = 0; i < regex.length; i++) {
			for(int j = 0; j < regex[i].length; j++) {
				Pattern p = Pattern.compile(regex[i][j]);
				Matcher m = p.matcher(regex[i][j]);
				if(m.matches()) {
					System.out.println("arabic numerals " + regex[i][j]);
					count = count + Integer.parseInt(regex[i][j]);
				}
			}
		}*/
		
		Pattern p = Pattern.compile(pattern[0]);
	    Matcher m = p.matcher(arr[0]);
	    Matcher m2 = p.matcher(arr[2]);
	    Pattern p1 = Pattern.compile(pattern[1]);
	    Matcher m1 = p1.matcher(arr[0]);
	    Matcher m3 = p1.matcher(arr[2]);
	    if((!m.matches() || !m2.matches()) && (!m1.matches() || !m3.matches())) {
	    	throw new InputException("different number systems are used simultaneously");
	    }
	    
		switch (i1) {
		case "+":
			try { 
				 i0 = Integer.parseInt(arr[0]);
				 i2 = Integer.parseInt(arr[2]);
				 result = String.valueOf(i0 + i2);
				} catch (NumberFormatException e) {
					  i0 = NumRim.valueOf(arr[0]).getNumArab();
					  i2 = NumRim.valueOf(arr[2]).getNumArab();
					  res = i0 + i2;
					  //if(res > 10) result = String.valueOf(res);
					  for(NumRim nr : NumRim.values()) 
						if(nr.getNumArab() == res)
					  	result = nr.name();
				} 
		break;
		case "-":
			try { 
				 i0 = Integer.parseInt(arr[0]);
				 i2 = Integer.parseInt(arr[2]);
				 result = String.valueOf(i0 - i2);
				} catch (NumberFormatException e) {
					  i0 = NumRim.valueOf(arr[0]).getNumArab();
					  i2 = NumRim.valueOf(arr[2]).getNumArab();
					  res = i0 - i2;
					  if(res < 1)
						  throw new InputException("there was no zero in the Roman numeral system");
					  for(NumRim nr : NumRim.values()) 
						if(nr.getNumArab() == res)
					  	result = nr.name();
				} 
		break;
		case "/":
			try { 
				 i0 = Integer.parseInt(arr[0]);
				 i2 = Integer.parseInt(arr[2]);
				 result = String.valueOf(i0 / i2);
				} catch (NumberFormatException e) {
					  i0 = NumRim.valueOf(arr[0]).getNumArab();
					  i2 = NumRim.valueOf(arr[2]).getNumArab();
					  res = i0 / i2;
					  if(res < 1)
						  throw new InputException("there was no zero in the Roman numeral system");
					  for(NumRim nr : NumRim.values()) 
						if(nr.getNumArab() == res)
					  	result = nr.name();
				} 
		break;
		case "*":
			try { 
				 i0 = Integer.parseInt(arr[0]);
				 i2 = Integer.parseInt(arr[2]);
				 result = String.valueOf(i0 * i2);
				} catch (NumberFormatException e) {
					  i0 = NumRim.valueOf(arr[0]).getNumArab();
					  i2 = NumRim.valueOf(arr[2]).getNumArab();
					  res = i0 * i2;
					//if(res > 10) result = String.valueOf(res);
					  for(NumRim nr : NumRim.values()) 
						if(nr.getNumArab() == res)
					  	result = nr.name();
				} 
		break;
		default:
			throw new InputException("there is no mathematical operator in the expression");
			
		}
		return result;
	}
}