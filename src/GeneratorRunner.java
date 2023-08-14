import scheduleuser.*;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;

class GeneratorRunner {


	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		scheduleuser.ScheduleGenerator a = new scheduleuser.ScheduleGenerator();
		String start;
		String end;
		int startMins;
		int endMins;
		int headway = 1;
		boolean next = true;
		while (next) {
			System.out.print("Input start time: ");
			start = input.nextLine();
			System.out.print("Input end time: ");
			end = input.nextLine();
			System.out.print("Input headway in mins: ");
			headway = input.nextInt();
			input.nextLine();
			System.out.println("Generating schedule: ");

			//System.out.println(start);
			//System.out.println(end);
			//System.out.println(headway);

			startMins = a.timeToMins(start);
			endMins = a.timeToMins(end);
			a.addToSchedule(a.minsToSchedule(startMins,endMins,headway));
			a.printSchedule();

			System.out.print("New input? (y/n): ");
			if (!Objects.equals(input.nextLine(), "y")){
				next = false;
			}


			//a.timeToMins(start);
			//a.minsToTime(1709);
		}
		System.out.println("Next step: Printing Schedule");

		input.close();
	}
}