package Experiment_5;

public class IllegalIndexException extends Throwable{

	@Override
	public String toString() {
		return "矩阵的行号或列号非法访问";
	}
	
}
