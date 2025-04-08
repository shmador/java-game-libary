import java.util.Stack;

public class Calculator {
	
	public static boolean isForm(String text) {
		return validParens(text, new Stack<Character>()) && 
				validOperands(text) && validNumbers(text);
	}
	
	private static boolean validParens(String text, Stack<Character> parens) {
		if (text.length() <= 0)
			return parens.isEmpty();
		char c = text.charAt(0);
		if (c == '(')
			parens.push(c);
		if (c == ')') {
			if (parens.isEmpty())
				return false;
			parens.pop();
		}
		return validParens(text.substring(1), parens);
	}
	private static boolean validOperands(String text) {
		char[] chars = text.toCharArray();
		char last = chars[chars.length-1];
		char first = chars[0];
		if (mulOrDiv(last) || mulOrDiv(first))
			return false;
		for (int i = 1; i < chars.length; i++) {
			if (mulOrDiv(chars[i]) && isOperand(chars[i-1]))
					return false;
			if (plusOrMinus(chars[i]) && plusOrMinus(chars[i-1]))
				return false;
		}
		return true;
	}

	private static boolean mulOrDiv(char c) {
		return c == '*' || c == '/';
	}
	private static boolean plusOrMinus(char c) {
		return c == '+' || c == '-';
	}
	private static boolean isOperand(char c) {
		return mulOrDiv(c) || plusOrMinus(c);
	}
	public static boolean isNumber(String text) {
		if (text.length() == 0)
			return false;
		char[] chars = text.toCharArray();
		char last = chars[chars.length-1];
		char first = chars[0];
		boolean hasDot = false;
		if (last == '.' || first == '.')
			if (!Character.isDigit(first) && plusOrMinus(first))
				return false;
		for (int i = 1; i < chars.length; i++) {
			char c = chars[i];
			if (!Character.isDigit(c)) {
				if (c != '.')
					return false;
				else if (chars[i-1] == '.' || hasDot)
					return false;
				hasDot = true;
			}
		}
		return true;
	}
	private static boolean validNumbers(String text) {
		int lastOp = 0;
		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (isOperand(c)) {
				if (lastOp != 0) {
					String num = text.substring(lastOp, i);
					if (!isNumber(num))
						return false;
				}
				lastOp = i;
			}
		}
		System.out.println("passed loop" + lastOp);
		return isNumber(text.substring(lastOp+1));
	}
	public static void main(String[] args) {
		String s = "1$%2*5";
		System.out.println(isForm(s));
	}
	
}
