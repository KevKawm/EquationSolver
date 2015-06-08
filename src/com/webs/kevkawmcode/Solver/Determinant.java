package com.webs.kevkawmcode.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Determinant {

	public static double get(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] array = input.nextLine().split(" ");
		List<List<Double>> matriz = new ArrayList<List<Double>>();
		List<Double> list = new ArrayList<Double>();
		for (String j : array) {
			try {
				list.add(Double.parseDouble(j));
			} catch (Exception e) {
				System.out.println("Error: input only numbers.");
				input.close();
				return Double.NaN;
			}
		}
		matriz.add(list);
		for (int i = 0; i < array.length - 1; i++) {
			String[] array1 = input.nextLine().split(" ");
			List<Double> list1 = new ArrayList<Double>();
			for (String j : array1) {
				try {
					list1.add(Double.parseDouble(j));
				} catch (Exception e) {
					input.close();
					return Double.NaN;
				}
			}
			matriz.add(list1);
		}
		input.close();
		return det(matriz);
	}
	
	public static double det(List<List<Double>> matriz) {
		if (matriz.size() == 1) return matriz.get(0).get(0);
		if (matriz.size() == 2) return (matriz.get(0).get(0) * matriz.get(1).get(1)) - (matriz.get(0).get(1) * matriz.get(1).get(0));
		double ret = 0;
		for (int j = 1; j <= matriz.size(); j++) {
			ret += Math.pow(-1, j + 1) * matriz.get(0).get(j - 1) * det(remove(matriz, 1, j));
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