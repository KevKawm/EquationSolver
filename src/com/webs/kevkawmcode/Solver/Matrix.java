package com.webs.kevkawmcode.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix {
	
	public List<List<Double>> matrix;
	
	public Matrix(List<List<Double>> matrix){
		this.matrix = matrix;
	}
	
	/*private List<Double> getRow(int i){
		for(List<Double> list : )
	}*/
	
	public void times(Matrix matrix){
		
	}
	
	public Matrix remove(int row, int column) {
		List<List<Double>> matrix = new ArrayList<List<Double>>();
		Collections.copy(matrix, this.matrix);
		List<List<Double>> ret = new ArrayList<List<Double>>();
		for (List<Double> list : matrix) {
			List<Double> list1 = new ArrayList<Double>();
			for (Double double1 : list) {
				list1.add(double1);
			}
			ret.add(list1);
		}
		ret.remove(row - 1);
		for (int i = 0; i < ret.size(); i++) {
			ret.get(i).remove(column - 1);
		}
		return new Matrix(matrix);
	}
	
	public double determinant() {
		List<List<Double>> matrixList = this.matrix;
		if (matrixList.size() == 1) return matrixList.get(0).get(0);
		if (matrixList.size() == 2) return (matrixList.get(0).get(0) * matrixList.get(1).get(1)) - (matrixList.get(0).get(1) * matrixList.get(1).get(0));
		double ret = 0;
		for (int j = 1; j <= matrixList.size(); j++) {
			ret += Math.pow(-1, j + 1) * matrixList.get(0).get(j - 1) * remove(1, j).determinant();
		}
		return ret;
	}
	
}
