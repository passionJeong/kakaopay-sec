package assignment2.algorithm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class assignmentMain {
	
	public static void main(String[]args){
		Scanner scanner = new Scanner(System.in);
		int question = 0;
		
		System.out.println(ConstantValue.QUESTION_INFORMATION);
		
		try {
			question = scanner.nextInt();
		} catch(InputMismatchException e) {
			System.out.println(ConstantValue.INVALID_QUESTION_NUM);
			scanner.close();
			return;
		}
		
		switch(question) {
			case 1:
				//1. 시간의 간격
				TimeInterval timeInterval = new TimeInterval();
				timeInterval.doTimeInterval();
				break;
			
			case 2:
				//2. 배열탐색
				ArrayExplore arrayExplore = new ArrayExplore();
				arrayExplore.doArrayExplore();
				break;
				
			case 3:
				//3. 배열 회전
				ArrayRotation arrayRotation = new ArrayRotation();
				arrayRotation.doArrayRotation();
				break;
				
			case 4:
				//4. 마지막 남는 자리
				LastSeat lastSeat = new LastSeat();
				lastSeat.doLastSeat();
				break;
				
			default:
				System.out.println(ConstantValue.INVALID_QUESTION_NUM);
				break;
				
		}
		
		scanner.close();
	}
}