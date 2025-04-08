
public class Exercise {
	public static void main(String[] args) {
		System.out.println(secret("MYJOBTEST", "JOB"));
		System.out.println(secret("MYJOBTEST", "JOOB"));
	}
	public static boolean secret (String s1, String s2) {
		int i = 0;
		while(s1.length()>0) {
			if (s1.charAt(0)==s2.charAt(i))
				i++;
			else
				i=0;
			if (i == s2.length())
				return true;
			s1 = s1.substring(1);
		}
		return false;
	}
}
