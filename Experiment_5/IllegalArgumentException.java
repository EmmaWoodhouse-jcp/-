package Experiment_5;

public class IllegalArgumentException extends Throwable{
	@Override
	public String toString() {
		return "行数和列数应该大于0,且不能传一个空的矩阵来初始化矩阵";
	}
}
