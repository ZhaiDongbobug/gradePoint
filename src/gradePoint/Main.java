package gradePoint;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int total = 0;
		Scanner s = new Scanner(System.in);
		total = s.nextInt();
		String[] resultArry = new String[total];
		for (int i = 0; i < total; i++) {
			Scanner sc = new Scanner(System.in);
			String[] numbers = sc.nextLine().split(" ");
			String result;
			int h = Integer.parseInt(numbers[0]);
			int n = Integer.parseInt(numbers[1]);
			int m = Integer.parseInt(numbers[2]);
			if (h < 20) {
				h = h - 10 * m;
			} else {
				while (n > 0) {
					h = (h / 2) + 10;
					n--;
				}
				h = h - 10 * m;
			}
			if (h <= 0) {
				result = "YES";
			} else {
				result = "NO";
			}
			resultArry[i] = result;
		}
		for (int i = 0; i < total; i++) {
			System.out.println(resultArry[i]);
		}
	}
}
