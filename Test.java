import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum NumRim{M(1000), CM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40), X(10), IX(9), VIII(8), VII(7), VI(6), V(5), IV(4), III(3), II(2), I(1);
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
		
		System.out.println("output:\n" + calc(input));
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
					  StringBuffer sb = new StringBuffer();
						for(NumRim nr : NumRim.values()) {
							while(res >= nr.getNumArab()) {
								res -= nr.getNumArab();
								sb.append(nr.name());
							}
						}
					  	result = String.valueOf(sb);
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
					  StringBuffer sb = new StringBuffer();
						for(NumRim nr : NumRim.values()) {
							while(res >= nr.getNumArab()) {
								res -= nr.getNumArab();
								sb.append(nr.name());
							}
						}
					  	result = String.valueOf(sb);
				} 
		break;
		default:
			throw new InputException("there is no mathematical operator in the expression");
			
		}
		return result;
	}
}