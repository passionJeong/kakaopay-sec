package assignment2.algorithm;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 3. 배열 회전
 * @author jeongseoyeon
 * 배열을 입력받아, 입력된 방향으로 회전을 하고 그에 따른 배열 값을 출력하시오
 */

public class ArrayRotation {

	public int doArrayRotation() {
		int answer = -1;
		Scanner scanner = new Scanner(System.in);
		int colSize;
		int rowSize;
		String arrayRawData;
		String rotationCommand;
		String printPosition;
		int[][] array;

		try {
			System.out.println(ConstantValue.INPUT_COL);
			colSize = scanner.nextInt();

			System.out.println(ConstantValue.INPUT_ROW);
			rowSize = scanner.nextInt();

			// 버퍼 비우기
			scanner.nextLine();

			System.out.println(ConstantValue.INPUT_ARRAY_DATA);
			arrayRawData = scanner.nextLine();

			System.out.println(ConstantValue.INPUT_ROTATE_COMMAND);
			rotationCommand = scanner.nextLine();

			System.out.println(ConstantValue.INPUT_PRINT_POSITION);
			printPosition = scanner.nextLine();

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
			
			for (int i = 0; i < rotationCommand.length(); i++) {
				switch (rotationCommand.charAt(i)) {
				case 'R':
					array = rightRotate(array);
					break;

				case 'L':
					array = leftRotate(array);
					break;

				case 'T':
					array = reverseRotate(array);
					break;
					
				default:
					//R, L, T외의 다른 문자가 왔을 경우 스킵
					break;
				}
			}
			
			arrayData = printPosition.replace(" ", "").split(",");
			
			//출력할 배열 위치가 제대로 입력되지 않았을 경우
			if(arrayData.length != 2) {
				System.out.println(ConstantValue.INVALID_POSITION);
				return answer;
			}
			
			int xPos = Integer.parseInt(printPosition.replace(" ", "").split(",")[0]);
			int yPos = Integer.parseInt(printPosition.replace(" ", "").split(",")[1]);
			answer = array[yPos - 1][xPos - 1];

		} catch(InputMismatchException e) {
			System.out.println(ConstantValue.INVALID_SIZE);
			return answer;
		} catch(NumberFormatException e) {
			System.out.println(ConstantValue.INVALID_INPUT);
			return answer;
		} finally {
			scanner.close();
		}
		
		System.out.println(answer);
		
		return answer;
	}

	// 오른쪽으로 90도 회전
	public int[][] rightRotate(int[][] array) {
		int newColumnSize = array.length;
		int newRowSize = array[0].length;
		int[][] rotatedArray = new int[newRowSize][newColumnSize];

		for (int i = 0; i < newRowSize; i++) {
			for (int j = 0; j < newColumnSize; j++) {
				rotatedArray[i][j] = array[newColumnSize - j - 1][i];
			}
		}

		return rotatedArray;
	}

	// 왼쪽으로 90도 회전
	public int[][] leftRotate(int[][] array) {
		int newColumnSize = array.length;
		int newRowSize = array[0].length;
		int[][] rotatedArray = new int[newRowSize][newColumnSize];

		for (int i = 0; i < newRowSize; i++) {
			for (int j = 0; j < newColumnSize; j++) {
				rotatedArray[i][j] = array[j][newRowSize - i - 1];
			}
		}

		return rotatedArray;
	}

	// 뒤집기
	public int[][] reverseRotate(int[][] array) {
		int newColumnSize = array[0].length;
		int newRowSize = array.length;
		int[][] rotatedArray = new int[newRowSize][newColumnSize];

		for (int i = 0; i < newRowSize; i++) {
			for (int j = 0; j < newColumnSize; j++) {
				rotatedArray[i][j] = array[i][newColumnSize - j - 1];
			}
		}

		return rotatedArray;
	}

}
