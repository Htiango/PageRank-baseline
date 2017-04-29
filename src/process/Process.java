/**
 * 
 */
package process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author hty
 *
 */

public class Process {
	private int nodeNum, linkNum;
	private HashMap<Integer, String> mapIndex;
	private double[][] connState;
	
	public void readInFile(String filePath){
        try{
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                if((lineTxt = bufferedReader.readLine()) != null){
                	String[] list = new String(lineTxt).split(" ");
                	System.out.println("nodeNum = " + list[0]);
                	System.out.println("linkNum = " + list[1]);
                	nodeNum = Integer.parseInt(list[0]);
                	linkNum = Integer.parseInt(list[1]);
                }
                
                mapIndex = new HashMap<Integer, String>();
                connState = new double[nodeNum][nodeNum];
                
                for(int i = 0; i < nodeNum; i++){
                	lineTxt = bufferedReader.readLine();
                	String[] list = new String(lineTxt).split(" ");
//                	System.out.println("int = " + list[0]);
//                	System.out.println("out = " + list[1]);
                	Integer key = new Integer(i);
                	String value = list[1];
                	mapIndex.put(key, value);
                }
                
                for(int i = 0; i < linkNum; i ++){
                	lineTxt = bufferedReader.readLine();
                	String[] list = new String(lineTxt).split(" ");
//                	System.out.println("int = " + list[0]);
//                	System.out.println("out = " + list[1]);
                	int linkToIndex = Integer.parseInt(list[0]);
                	int linkFromIndex = Integer.parseInt(list[1]);
                	connState[linkFromIndex - 1][linkToIndex - 1] = 1;
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("Wrong file path!  ");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in reading file");
            e.printStackTrace();
        }
    }
	
	public void writeInFile(String filePath, double[][] input){
		try {
			double sum = 0.0;
			File file = new File(filePath);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < input.length; i++){
				out.write(i + "  pageRank value = " + input[i][0]);
				sum += input[i][0];
				out.newLine();
			}
			System.out.println("sum = " + sum);
			out.close();
		} catch (Exception e) {
			System.out.println("Error in writing! ");
		}
	}
	
	public int getNodeNum(){
		return nodeNum;
	}
	
	public int getLinkNum(){
		return linkNum;
	}
	
	public HashMap<Integer, String> getMap() {
		return mapIndex;
	} 
	
	public double[][] getConnState(){
		return connState;
	}
}
