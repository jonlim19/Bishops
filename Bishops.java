
public class Bishops {

	private static int numbishops = 3;//we start with 3 bishops
	private static int numbishopsplaced = 0;
	private static int lastrowposition = -1;
	private static int lastcolposition = -1;
	private static int matrixSize = numbishops;
	private static int numsolutions = 1; 
	private static String[][] board = new String[matrixSize][matrixSize];// we have a 3 x 3 chessboard
	private static int[][] attackboard = new int[matrixSize][matrixSize];// indicates the field of attack of each bishop
	private static boolean[][] res = new boolean[matrixSize][matrixSize];



	public static void print() {
		System.out.printf("%d-\n", numsolutions++);
		for (int i = 0; i < matrixSize; i++)  {  
			for (int j = 0; j < matrixSize; j++) {
				System.out.print(board[i][j] + " ");
			} 
			System.out.printf("\n");  
		}
	}
	/*This function is responsible for marking all the 'unsafe' areas to place the subsequent bishops*/
	public static void setattackpath(int[][]attack, int row, int col,boolean toggle) {
		int i, j;  

		for (i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
			if(toggle==true) {
				attack[i][j] += 1;
			}else {
				attack[i][j] -= 1;
			}

		}  

		for (i = row-1, j = col+1; j < matrixSize && i >= 0; i--, j++) {
			if(toggle==true) {
				attack[i][j] += 1;
			}else {
				attack[i][j] -= 1;
			}
		}  

		for (i = row+1, j = col-1; j >= 0 && i < matrixSize; i++, j--) {
			if(toggle==true) {
				attack[i][j] += 1;
			}else {
				attack[i][j] -= 1;
			}
		}

		for (i = row+1, j = col+1; j < matrixSize && i < matrixSize; i++, j++) {
			if(toggle==true) {
				attack[i][j] += 1;
			}else {
				attack[i][j] -= 1;
			}
		}


	}
	/*Function for placing a bishop*/
	public static void place(int row, int col) {
		board[row][col] = "B";
		res[row][col] = true;//indicates that there is bishop placed in these coordinates
		setattackpath(attackboard,row,col,true);

	}
	/**/
	public static void remove(int row, int col) {
		board[row][col] = "*";
		res[row][col] = false;// indicates the bishop has been removed
		setattackpath(attackboard,row,col,false);
	}
	/*Checks if its safe to place a bishop at that location*/
	public static boolean check(int row,int col) {
		boolean res = false;
		if(attackboard[row][col]>0) {
			res = false;
		}else {
			res = true;
		}


		return res;
	}
	/*The backtracking function*/
	public static void solve(int r) {
		int row = r/matrixSize;
		int col = r%matrixSize;
		for(int i = row; i < matrixSize; i++) {
			for(int j = col; j < matrixSize; j++) {
				col = 0;
				place(i,j);
				int lastposition = (i*matrixSize)+j;
				numbishopsplaced++;
				if(check(i,j)==true) {
					if(numbishopsplaced==numbishops) {
						print();
						//solve(lastrowposition+1, lastcolposition+1);
					}else {
						solve(lastposition+1);
					}

				}
				remove(i,j);
				numbishopsplaced--;
			}
		}
	}
	public static void main(String[] args) {
		/*place(2,0);
		//remove(0,1);
		print();*/
		for (int i = 0; i < matrixSize; i++)  {  
			for (int j = 0; j < matrixSize; j++) {
				board[i][j] = "*";
			} 

		}
		solve(0);//we place the bishop at this location

	}
}
