package Experiment_5;

public class MatrixMultiplicationException extends Throwable{

	@Override
	public String toString() {
		return "俩矩阵有空或者它们的行数和列数不满足相乘的条件故无法相乘";
	}
	
}
