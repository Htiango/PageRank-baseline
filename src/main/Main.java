/**
 * @author hty
 */
package main;

import pageRank.PageRank;
import process.Process;

public class Main {

	public static void main(String[] args){
//		String readPath = "hollins.dat";
//		String writePath = "hollins-result.dat";
		
		String readPath = "test-data2.dat";
		String writePath = "test-data2-result.dat";
		
		double paraD= 0.85;
		double threshold = 0.0000000001;
//		int step = 40;
		
		PageRank pageRank = new PageRank();
		Process process = new Process();
		
		process.readInFile(readPath);
		
		int nodeNum = process.getNodeNum();
		double[][] connState = process.getConnState();
//		for(int i = 0; i < nodeNum; i++){
//			for(int j = 0; j < nodeNum; j++){
//				System.out.print(connState[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		double[][] matrixConn = pageRank.initConnState(connState);
		double[][] initMatrix = pageRank.initMatrix(nodeNum);
		
		pageRank.setParameterD(paraD);
		pageRank.setThreshold(threshold);
		
		double[][] result = pageRank.getPageRank(initMatrix, matrixConn);
		process.writeInFile(writePath, result);
		System.out.println("Finished !");
//		for(int i = 0; i < nodeNum; i++){
//			System.out.println(result[i][0]);
//		}
	}

}
