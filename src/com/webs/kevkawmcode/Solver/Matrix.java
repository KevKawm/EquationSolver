package com.webs.kevkawmcode.Solver;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	
	public List<List<Double>> matrix;
	
	public Matrix(List<List<Double>> matrix){
		this.matrix = matrix;
	}
	
	public Matrix() {
		this.matrix = new ArrayList<List<Double>>();
	}

	public Matrix times(Matrix matrix2){
		List<List<Double>> matrix1 = this.matrix;
		if (matrix1.get(0).size() != matrix2.rows()) return null;
		List<List<Double>> out = new ArrayList<List<Double>>();
		for (int i = 0; i < matrix1.size(); i++) {
			List<Double> add = new ArrayList<Double>();
			for (int j = 0; j < matrix2.columns(); j++) {
				double iadd = 0;
				for (int k = 0; k < matrix2.rows(); k++) {
					iadd += matrix1.get(i).get(k) * matrix2.get(k).get(j);
				}
				add.add(iadd);
			}
			out.add(add);
		}
		return new Matrix(out);
	}
	
	public Matrix times(Double k) {
		List<List<Double>> matrix = this.matrix;
		if (matrix.isEmpty()) return new Matrix(matrix);
		Matrix ret = new Matrix(new ArrayList<List<Double>>());
		for (int i = 0; i < matrix.size(); i++) {
			List<Double> add = new ArrayList<Double>();
			for (int j = 0; j < matrix.get(0).size(); j++) {
				add.add(matrix.get(i).get(j) * k);
			}
			ret.add(add);
		}
		return ret;
	}
	
	public Matrix remove(int rowIndex, int columnIndex) {
		List<List<Double>> ret = new ArrayList<List<Double>>();
		for (List<Double> list : this.matrix) {
			List<Double> list1 = new ArrayList<Double>();
			for (Double double1 : list) {
				list1.add(double1);
			}
			ret.add(list1);
		}
		ret.remove(rowIndex);
		for (int i = 0; i < ret.size(); i++) {
			ret.get(i).remove(columnIndex);
		}
		return new Matrix(ret);
	}
	
	public double determinant() {
		if (!new Matrix(this.matrix).isSquare()) return Double.NaN;
		List<List<Double>> matrixList = this.matrix;
		if (matrixList.size() == 1) return matrixList.get(0).get(0);
		if (matrixList.size() == 2) return (matrixList.get(0).get(0) * matrixList.get(1).get(1)) - (matrixList.get(0).get(1) * matrixList.get(1).get(0));
		double ret = 0;
		for (int j = 0; j < matrixList.size(); j++) {
			ret +=  matrixList.get(0).get(j) * coFactor(0, j);
		}
		return ret;
	}
	
	public Matrix transpose() {
		List<List<Double>> matrix = this.matrix;
		Matrix ret = new Matrix(new ArrayList<List<Double>>());
		for (int i = 0; i < matrix.get(0).size(); i++) {
			List<Double> add = new ArrayList<Double>();
			for (int j = 0; j < matrix.size(); j++) {
				add.add(matrix.get(j).get(i));
			}
			ret.add(add);
		}
		return ret;
	}
	
	public Matrix inverse() {
		if (this.matrix.isEmpty()) return null;
		if (new Matrix(this.matrix).determinant() == 0) return null;
		List<List<Double>> coFactors = new ArrayList<List<Double>>();
		List<List<Double>> matrix = this.matrix;
		for (int i = 0; i < matrix.size(); i++) {
			List<Double> add = new ArrayList<Double>();
			for (int j = 0; j < matrix.get(0).size(); j++) {
				add.add(new Matrix(matrix).coFactor(i, j));
			}
			coFactors.add(add);
		}
		return new Matrix(coFactors).transpose().times(1 / new Matrix(matrix).determinant());
	}
	
	public static Matrix create(List<Double> list) {
		if (Math.sqrt(list.size()) % 1 != 0) return null;
		Matrix ret = new Matrix();
		for (int i = 0; i < Math.sqrt(list.size()); i++) {
			List<Double> add = new ArrayList<Double>();
			for (int j = 0; j < Math.sqrt(list.size()); j++) {
				add.add(list.get((int) (i * Math.sqrt(list.size()) + j)));
			}
			ret.add(add);
		}
		return ret;
	}
	
	public static Matrix create(Double[] array) {
		if (Math.sqrt(array.length) % 1 != 0) return null;
		Matrix ret = new Matrix();
		for (int i = 0; i < Math.sqrt(array.length); i++) {
			List<Double> add = new ArrayList<Double>();
			for (int j = 0; j < Math.sqrt(array.length); j++) {
				add.add(array[(int) (i * Math.sqrt(array.length) + j)]);
			}
			ret.add(add);
		}
		return ret;
	}
	
	public static Matrix create(int[] array) {
		if (Math.sqrt(array.length) % 1 != 0) return null;
		Matrix ret = new Matrix();
		for (int i = 0; i < Math.sqrt(array.length); i++) {
			List<Integer> add = new ArrayList<Integer>();
			for (int j = 0; j < Math.sqrt(array.length); j++) {
				add.add(array[(int) (i * Math.sqrt(array.length) + j)]);
			}
			ret.addInt(add);
		}
		return ret;
	}
	
	public static Matrix identiy(int size) {
		Matrix matrix = new Matrix();
		for (int i = 0; i < size; i++) {
			List<Double> add = new ArrayList<Double>();
			for (int j = 0; j < size; j++) {
				if (j == i) add.add(1D);
				else add.add(0D);
			}
			matrix.add(add);
		}
		return matrix;
	}
	
	public int rows() {
		List<List<Double>> matrix = this.matrix;
		return matrix.size();
	}
	
	public int columns() {
		List<List<Double>> matrix = this.matrix;
		if (matrix.isEmpty()) return 0;
		return matrix.get(0).size();
	}
	
	public void print() {
		List<List<Double>> matrix = this.matrix;
		System.out.println(matrix);
	}
	
	public List<Double> get(int i) {
		List<List<Double>> matrix = this.matrix;
		return matrix.get(i);
	}
	
	public boolean isSquare() {
		if (this.matrix.isEmpty()) return false;
		return this.matrix.size() == this.matrix.get(0).size();
	}
	
	public void add(List<Double> list) {
		this.matrix.add(list);
	}
	
	public void addInt(List<Integer> list) {
		List<Double> dList = new ArrayList<Double>();
		for (int i : list) {
			dList.add((double) i);
		}
		this.matrix.add(dList);
	}
	
	public double coFactor(int rowIndex, int columnIndex) {
		if (!new Matrix(this.matrix).isSquare()) return Double.NaN;
		return Math.pow(-1, rowIndex + columnIndex) * new Matrix(this.matrix).remove(rowIndex, columnIndex).determinant();
	}
}