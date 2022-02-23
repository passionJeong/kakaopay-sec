package assignment2.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 1. 시간의 간격
 * @author jeongseoyeon
 * 시:분(HH:MM) 형태의 두 시간을 입력 받은 후, 두 시간 사이의 간격(분min)을 구하시오
 */

public class TimeInterval {

	public Integer doTimeInterval() {
		Integer answer = 0;
		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
		Date startTime;
		Date endTime;

		try {
			System.out.println(ConstantValue.INPUT_FIRST_TIME);
			startTime = dateFormat.parse(scanner.nextLine());

			System.out.println(ConstantValue.INPUT_SECOND_TIME);
			endTime = dateFormat.parse(scanner.nextLine());

			// java Date클래스 getTime() 메소드를 사용하여 시간 차이를 구한다
			Long timeDiff = endTime.getTime() - startTime.getTime();
			timeDiff = timeDiff / (60 * 1000);
			answer = timeDiff.intValue();

			// 시작시간이 끝시간보다 클 경우 다음 날로 넘어가야하므로 최종 시간차에 24시간에 해당하는 값을 더한다.
			// 예) 00:41 -> 24:41, 06:30 -> 30:30
			if (startTime.getTime() > endTime.getTime()) {
				answer += 24 * 60;
			}

		} catch (ParseException e) {
			System.out.println(ConstantValue.INVALID_TIME);
			return -1;
		} finally {
			scanner.close();
		}

		System.out.println(answer);
		
		return answer;
	}

}
