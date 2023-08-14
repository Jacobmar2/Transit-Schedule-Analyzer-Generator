import scheduleuser.*;
import java.util.Scanner;

class AnalyzerRunner {


	public static void main(String[] args) {
		System.out.print("Input schedule you want to analyze: ");
		Scanner input = new Scanner(System.in);
		String schedule = input.next();
		System.out.println(schedule);
		input.close();
	}
}