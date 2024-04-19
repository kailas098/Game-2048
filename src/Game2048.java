import java.util.Scanner;

public class Game2048 {

	private final static int size = 4;
	int[][] board;

	public Game2048() {
		this.board = createBoard(size);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Game2048 game2048 = new Game2048();

		System.out.print("""
				Welcome
				W - Move Up.
				A - Move Left.
				S - Move Down.
				D - Move Right.
				Type 'Exit' to exit.
				""");

		game2048.generateNewTile();
		game2048.generateNewTile();
		game2048.printBoard();

		System.out.println("Enter Your choice:");
		String choice = sc.next().toUpperCase();

		while (true) {
			int res = 0;
			switch (choice) {
				case "A": {
					res = game2048.moveLeft();
					break;
				}
				case "D": {
					res = game2048.moveRight();
					break;
				}
				case "W": {
					res = game2048.moveUp();
					break;
				}
				case "S": {
					res = game2048.moveDown();
					break;
				}
				case "EXIT": {
					System.out.println("You Quit.");
					break;
				}
				default: {
					System.out.println("Wrong Option");
					break;
				}
			}

			if (res == -1) {
				System.out.println("You Lost.");
				break;
			}
			else if (res == 1) {
				System.out.println("You Won.");
				game2048.printBoard();
				break;
			}
			if (choice.equals("EXIT"))
				break;

			System.out.print("""
					W - Move Up.
					A - Move Left.
					S - Move Down.
					D - Move Right.
					Type 'Exit' to exit.
					""");

			game2048.generateNewTile();
			game2048.printBoard();

			System.out.println("Enter Your choice:");
			choice = sc.next().toUpperCase();
		}


	}

	public int moveRight() {
		for (int[] row : this.board) {
			combineRight(row);
		}

		return won();
	}

	public int moveLeft() {
		for (int[] row : this.board)
			combineLeft(row);

		return won();
	}

	public int moveUp() {
		for (int col = 0; col < size; col++) {
			combineUp(col);
		}

		return won();
	}

	public int moveDown() {
		for (int col = 0; col < size; col++) {
			combineDown(col);
		}

		return won();
	}

	public void combineRight(int[] arr) {
		int nextPos = arr.length - 1;
		for (int i = arr.length - 1; i >= 0; ) {
			if (arr[i] != 0) {

				int ind = i;
				int val1 = arr[i--];
				while (i >= 0 && arr[i] == 0)
					i--;

				int val2 = (i >= 0) ? arr[i] : 0;
				arr[ind] = 0;

				if (val1 == val2) {
					arr[i] = 0;
					arr[nextPos--] = val1 + val2;
					i--;
				} else {
					if (i >= 0) arr[i] = 0;
					arr[nextPos--] = val1;

					if (nextPos >= 0) arr[nextPos] = val2;
					i = nextPos;

				}
			} else
				i--;
		}
	}

	public void combineLeft(int[] arr) {
		int nextPos = 0;
		int len = arr.length;

		for (int i = 0; i < len; ) {
			if (arr[i] != 0) {
				int ind = i;
				int val1 = arr[i++];

				while (i < len && arr[i] == 0)
					i++;

				int val2 = (i < len) ? arr[i] : 0;
				arr[ind] = 0;

				if (val1 == val2) {
					arr[i++] = 0;
					arr[nextPos++] = val1 + val2;
				} else {
					if (i < len) arr[i] = 0;
					arr[nextPos++] = val1;

					if (nextPos < len) arr[nextPos] = val2;
					i = nextPos;
				}
			} else
				i++;
		}
	}

	public void combineUp(int col) {
		int nextPos = 0;

		for (int i = 0; i < size; ) {


			if (this.board[i][col] != 0) {
				int ind = i;
				int val1 = this.board[i++][col];

				while (i < size && this.board[i][col] == 0)
					i++;

				int val2 = (i < size) ? this.board[i][col] : 0;
				this.board[ind][col] = 0;

				if (val1 == val2) {
					this.board[i][col] = 0;
					this.board[nextPos++][col] = val1 + val2;
					i++;
				} else {
					if (i < size)
						this.board[i][col] = 0;

					this.board[nextPos++][col] = val1;
					if (nextPos < size)
						this.board[nextPos][col] = val2;
					i = nextPos;
				}
			} else
				i++;
		}
	}

	public void combineDown(int col) {
		int nextPos = size - 1;

		for (int i = size - 1; i >= 0; ) {

			if (this.board[i][col] != 0) {
				int ind = i;
				int val1 = this.board[i--][col];

				while (i >= 0 && this.board[i][col] == 0)
					i--;

				int val2 = (i >= 0) ? this.board[i][col] : 0;

				this.board[ind][col] = 0;
				if (val1 == val2) {
					this.board[i][col] = 0;
					this.board[nextPos--][col] = val1 + val2;
					i--;
				} else {
					if (i >= 0)
						this.board[i][col] = 0;
					this.board[nextPos--][col] = val1;

					if (nextPos >= 0)
						this.board[nextPos][col] = val2;

					i = nextPos;
				}

			} else
				i--;
		}
	}


	public void generateNewTile() {
		int row = (int) (Math.random() * 4);
		int col = (int) (Math.random() * 4);

		while (this.board[row][col] != 0) {
			row = (int) (Math.random() * 4);
			col = (int) (Math.random() * 4);
		}

		this.board[row][col] = 2;
	}

	public int won() {
		boolean isFull = true;

		for (int[] arr : this.board) {
			for (int a : arr) {
				if (a == 2048)
					return 1;

				if (a == 0)
					isFull = false;
			}
		}
		if (isFull)
			return -1;

		return 0;
	}

	public int[][] createBoard(int n) {
		int[][] board = new int[n][];

		for (int i = 0; i < n; i++) {
			board[i] = new int[n];
		}

		return board;
	}

	public void printBoard() {
		for (int[] arr : this.board) {
			for (int a : arr) {
				if (a == 0)
					System.out.print("|     ");
				else
					System.out.print("| " + getString(a));
			}
			System.out.println("|");
		}
		System.out.println();
	}

	public String getString(int a) {
		String s = Integer.toString(a);
		int len = s.length();

		return s + " ".repeat(Math.max(0, 4 - len));
	}
}
