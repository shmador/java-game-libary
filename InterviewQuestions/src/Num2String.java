import java.util.HashMap;
import java.util.Map;

public class Num2String {
	
	public static void main (String[] args) {
		System.out.println(convert("seventy three hundred one"));
	}
	
	public static int convert(String number) {
		Map <String, Integer> map = new HashMap<>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);
		map.put("ten", 10);
		map.put("eleven", 11);
		map.put("twelve", 12);
		map.put("thirteen", 13);
		map.put("fourteen", 14);
		map.put("fifteen", 15);
		map.put("sixteen", 16);
		map.put("seventeen", 17);
		map.put("eighteen", 18);
		map.put("nineteen", 19);
		map.put("twenty", 20);
		map.put("thirty", 30);
		map.put("forty", 40);
		map.put("fifty", 50);
		map.put("sixty", 60);
		map.put("seventy", 70);
		map.put("eighty", 80);
		map.put("ninety", 90);
		map.put("hundred", 100);
		map.put("thousand", 1000);
		
		String[] arr = number.split(" ");
		
		int res = 0;
		int num = 0;
		int last = 0;

		for (int i = 0; i < arr.length; i++) {
			Integer n = map.get(arr[i]);
			if (n != null) {
				if (last > n && last == 1000 || last == 100) {
					System.out.println(num);
					res += num;
					num = 0;
				}
				if (n == 100 || n == 1000) 
					num *= n;
				else 
					num += n;
				last = n;
			}
		}

		return res + num;
		
	}
	
}
