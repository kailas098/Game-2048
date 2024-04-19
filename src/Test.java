public class Test {
	public static void main(String[] args) {
		Game2048 game2048 = new Game2048();

		game2048.board = new int[][]{
				{2, 2, 4, 4},
				{2, 4, 2, 4},
				{2, 0, 4, 4},
				{2, 2, 2, 2}
		};

		game2048.moveLeft();
		game2048.printBoard();

		game2048.moveRight();
		game2048.printBoard();

		game2048.moveUp();
		game2048.printBoard();

		game2048.moveDown();
		game2048.printBoard();
	}
}
