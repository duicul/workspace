package math;

public class MathReal implements Math {

	public MathReal() {
	}

	public float do_add(float a, float b) {
		return a+b;
	}

	public float do_sqrt(float a) {
		return (float) java.lang.Math.sqrt(a);
	}

}
