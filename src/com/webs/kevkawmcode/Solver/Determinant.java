package com.webs.kevkawmcode.Solver;

import java.util.ArrayList;
import java.util.List;

public class Determinant {
	
	public static double get(List<List<Double>> matrix) {
		if (matrix.size() == 1) return matrix.get(0).get(0);
		if (matrix.size() == 2) return (matrix.get(0).get(0) * matrix.get(1).get(1)) - (matrix.get(0).get(1) * matrix.get(1).get(0));
		double ret = 0;
		for (int j = 1; j <= matrix.size(); j++) {
			ret += Math.pow(-1, j + 1) * matrix.get(0).get(j - 1) * get(remove(matrix, 1, j));
		}
		return ret;
	}
	
	public static List<List<Double>> remove(List<List<Double>> matriz, int linha, int coluna) {
		List<List<Double>> ret = new ArrayList<List<Double>>();
		for (List<Double> list : matriz) {
			List<Double> list1 = new ArrayList<Double>();
			for (Double double1 : list) {
				list1.add(double1);
			}
			ret.add(list1);
		}
		ret.remove(linha - 1);
		for (int i = 0; i < ret.size(); i++) {
			ret.get(i).remove(coluna - 1);
		}
		return ret;
	}
}