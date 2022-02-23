package assignment2.algorithm;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 2. 배열탐색
 * @author jeongseoyeon
 * 배열을 다음 절차대로 탐색하여 그 결과를 출력하시오
 */

public class ArrayExplore {

	ArrayList<Integer> answerList;
	int[][] array;
	int colSize;
	int rowSize;
	int curRow = 0;
	int curCol = 0;

	public StringBuilder doArrayExplore() {
		Scanner scanner = new Scanner(System.in);
		String arrayRawData;
		StringBuilder answer = new StringBuilder();

		try {
			System.out.println(ConstantValue.INPUT_COL);
			colSize = scanner.nextInt();

			System.out.println(ConstantValue.INPUT_ROW);
			rowSize = scanner.nextInt();

			// 버퍼 비우기
			scanner.nextLine();

			System.out.println(ConstantValue.INPUT_ARRAY_DATA);
			arrayRawData = scanner.nextLine();

			answerList = new ArrayList<>(rowSize * colSize);

			array = new int[rowSize][colSize];
			
			String[] arrayData = arrayRawData.replace(" ", "").split(",");
			
			//배열 데이터의 원소개수가 가로x세로 개수와 일치하지 않을 경우
			if(rowSize * colSize != arrayData.length) {
				System.out.println(ConstantValue.INVALID_INPUT_SIZE);
				return answer;
			}
			
			// 배열을 채워넣는다.
			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < colSize; j++) {
					array[i][j] = Integer.parseInt(arrayData[(i * colSize) + j]);
				}
			}
			
			explore();

		} catch(InputMismatchException e) {
			System.out.println(ConstantValue.INVALID_SIZE);
			return answer;
		} catch(NumberFormatException e) {
			System.out.println(ConstantValue.INVALID_INPUT);
			return answer;
		} finally {
			scanner.close();
		}

		// ArrayList의 원소를 문제 조건에 맞는 형식으로 만들어준다.
		for (Integer exploreElement : answerList) {
			answer.append(exploreElement).append(",");
		}
		answer.deleteCharAt(answer.lastIndexOf(","));
		
		System.out.println(answer);
		
		return answer;
	}

	public void explore() {

		answerList.add(array[curRow][curCol]);
		
		//현재 좌표가 가장 마지막좌표에 도달하지 않는 동안 수행
		while (!(curCol == colSize - 1 && curRow == rowSize - 1)) {

			// 현재 x좌표가(curCol) 가로길이보다 작은 상태면 x좌표 증가(오른쪽으로 이동) 아니라면 y좌표 증가(아래로 이동)
			if (curCol + 1 < colSize) {
				curCol++;
			} else {
				curRow++;
			}

			answerList.add(array[curRow][curCol]);

			if (curCol == colSize - 1 && curRow == rowSize - 1) {
				break;
			}

			// 좌하향으로 이동
			while (curCol - 1 > -1 && curRow + 1 < rowSize) {
				answerList.add(array[++curRow][--curCol]);
			}

			// 현재 y좌표가(curRow) 세로길이보다 작은 상태면 y좌표 증가(아래로 이동) 아니라면 x좌표 증가(오른쪽으로 이동)
			if (curRow + 1 < rowSize) {
				curRow++;
			} else {
				curCol++;
			}

			answerList.add(array[curRow][curCol]);

			// 우상향으로 이동
			while (curRow - 1 > -1 && curCol + 1 < colSize) {
				answerList.add(array[--curRow][++curCol]);
			}

		}
	}

}
