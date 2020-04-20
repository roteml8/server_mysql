package main.recommendation;

import java.time.LocalDate;

public class Main {
	
	public static void main(String[]args)
	{
		LocalDate today = LocalDate.now();
		System.out.println(today);
		LocalDate week3 = today.minusDays(7);
		LocalDate week2 = today.minusDays(14);
		LocalDate week1 = today.minusDays(21);
		System.out.println(week3);
		System.out.println(week2);
		System.out.println(week1);
		LocalDate date = LocalDate.of(2020, 4, 6);
		System.out.println(week2.equals(date));
		System.out.println(4/(double)10);
				
	}

}
