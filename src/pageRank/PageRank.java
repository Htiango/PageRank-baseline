/**
 * @author hty
 * 
 */

package pageRank;

public class PageRank {
	
	private double threshold;
	private double paraD;
	private int step;
	
	/**
	 * set the value of threshold, remember d is always smaller than 1.
	 * @param input
	 */
	public void setThreshold(double input){
		if(input > 1 || input <= 0){
			System.out.println("Error!! Have a wrong input!!");
		}
		this.threshold = input;
	}
	
	/**
	 * set the value of parameter d, remember d is always smaller than 1.
	 * @param input
	 */
	public void setParameterD(double input) {
		if(input > 1 || input <= 0){
			System.out.println("Error!! Have a wrong input!!");
		}
		this.paraD = input;
	}
	
	public void setStep(int input){
		if(input <= 0){
			System.out.println("Error!! Have a wrong input!!");
		}
		step = input;
	}
	
	/**
	 * The core method of pageRank
	 * @param matrixInit The initialize value
	 * @param matrixConn The connection state, shall never change
	 * @return
	 */
	public double[][] getPageRank(double[][] matrixInit, double[][] matrixConn) {
		double[][] matrixPre = matrixInit;
		double[][] matrixNow = iterateStep(matrixInit, matrixConn);
		int count = 0;
		while(!isConverge(matrixPre, matrixNow)){
			matrixPre = matrixNow;
			matrixNow = iterateStep(matrixNow, matrixConn);
			count += 1;
		}
		System.out.println("count = " + count);
		return matrixNow;
	}
	
	public double[][] initConnState(double[][] matrix){
		int size = matrix.length;
		double[][] result = matrix;
		for(int i = 0; i < size; i++){
			int sum = 0;
			for(int j = 0; j < size; j++){
				if(matrix[j][i] == 1)
					sum += 1;
			}
			if(sum > 0){
				for(int j = 0; j < size ; j++){
					result[j][i] = matrix[j][i] / sum;
				}
			}
		}
		return result;
	}
	
	public double[][] initMatrix(int size){
		double[][] result = new double[size][1];
		for(int i = 0; i < size; i++){
			result[i][0] = 1.0 / size;
		}
		return result;
	}
	/**
	 * What we do in each step
	 * @param matrixPre
	 * @param matrixConn
	 * @return
	 */
	private double[][] iterateStep(double[][] matrixPre, double[][] matrixConn){
		double[][] result = matrixMul(matrixConn, matrixPre);
		return result;
	}
	
	
	/**
	 * judging whether the Markov Chain is converge 
	 * We should know that this judgment is used specifically for this program.
	 * @param matrixPre The previous values
	 * @param matrixNow The present values
	 * @return true if converge
	 */
	private boolean isConverge(double[][] matrixPre, double[][] matrixNow){
		int size = matrixPre.length;
		double difSum = 0;
		for(int i = 0; i < size; i++){
			difSum += Math.abs(matrixPre[i][0] - matrixNow[i][0]);
		}
		if(difSum > this.threshold){
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used for calculating the matrix multiplication step in pageRank. Here we suppose all the input is true.
	 * @param matrix1: size n * n
	 * @param matrix2: size n * 1
	 * @return result matrix with size n1 * m2
	 */
	private double[][] matrixMul(double[][] matrix1, double[][] matrix2){
		int N = matrix1.length;
		double[][] result = new double[N][1];
		
		for(int i = 0; i < N; i++){
			result[i][0] = singleValue(matrix1, matrix2, i, 0) * paraD + (1 - paraD) / N;
		}
		return result;
	}
	
	
	/**
	 * A method to calculate value for a single position in matrix multiplication
	 * @param matrix1 matrix 1
	 * @param matrix2 matrix 2
	 * @param row the row index of matrix1
	 * @param col the col index of matrix2
	 * @return the value of result at row 'row', col 'col'
	 */
	private double singleValue(double[][] matrix1, double[][] matrix2, int row, int col){
		double result = 0;	
		int sameDim = matrix1[0].length;
		
		for(int i = 0; i < sameDim; i++){
			result += matrix1[row][i] * matrix2[i][col];
		}
		return result;
	}
}
