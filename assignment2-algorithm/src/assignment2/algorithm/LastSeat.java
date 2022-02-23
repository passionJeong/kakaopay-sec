package assignment2.algorithm;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 4. 마지막 남는 자리
 * @author jeongseoyeon
 * N명의 친구가 둘러 앉아 있다 주워진 숫자만큼 이동해서 한명씩 탈락하면, 마지막으로 남는 친구는 누구인가?
 */

public class LastSeat {

	public Integer doLastSeat() {
		Integer answer = -1;
		Scanner scanner = new Scanner(System.in);
		Integer peopleNum;
		Integer moveCount;
		Queue<Integer> seatList = new LinkedList<Integer>();

		try {
			System.out.println(ConstantValue.INPUT_PEOPLE_NUM);
			peopleNum = scanner.nextInt();

			System.out.println(ConstantValue.INPUT_MOVE_COUNT);
			moveCount = scanner.nextInt();

			// 입력받은 사람수만큼 Queue를 채운다.
			for (int i = 1; i <= peopleNum; i++) {
				seatList.offer(i);
			}

			while (seatList.size() != 1) {
				// 이동할 자리수만큼 Queue의 첫번 째 원소를 제거하면서 제일 뒤에 삽입한다.
				for (int i = 1; i < moveCount; i++) {
					seatList.offer(seatList.poll());
				}

				// Queue의 첫번 째 원소를 제거한다(탈락).
				seatList.poll();
			}

			// while을 빠져나오면서 1개만 남았으므로 마지막으로 남는 친구다.
			answer = seatList.poll();

		} catch(InputMismatchException e) {
			System.out.println(ConstantValue.INVALID_SIZE);
			return answer;
		} finally {
			scanner.close();
		}
		
		System.out.println(answer);
		
		return answer;
	}
}
