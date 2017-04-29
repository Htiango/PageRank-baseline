/**
 * @author hty
 * 
 */

package pageRank;

public class PageRank {
	
	double threshold;
	
	
	/**
	 * set the value of threshold
	 * @param input
	 */
	public void setThreshold(double input){
		this.threshold = input;
	}
	
	
	
	
	/**
	 * judging whether the Markov Chain is converge
	 * @param matrixPre The previous values
	 * @param matrixNow The present values
	 * @return true if converge
	 */
	private boolean isConverge(double[] matrixPre, double[] matrixNow){
		int size = matrixPre.length;
		double difSum = 0;
		for(int i = 0; i < size; i++){
			difSum += Math.abs(matrixPre[i] - matrixNow[i]);
		}
		if(difSum > this.threshold){
			return false;
		}
		return true;
	}
	
	/**
	 * This method is used for calculating the matrix multiplication. Here we suppose all the input is true.
	 * @param matrix1: size n1 * d
	 * @param matrix2: size d * m2
	 * @return result matrix with size n1 * m2
	 */
	private double[][] matrixMul(double[][] matrix1, double[][] matrix2){
		int row = matrix1.length;
		int col = matrix2[0].length;
		double[][] result = new double[row][col];
		
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				result[i][j] = singleValue(matrix1, matrix2, i, j);
			}
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
