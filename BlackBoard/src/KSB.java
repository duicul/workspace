import java.util.List;


public class KSB extends KS {

	public KSB(BlackBoard b) {
		super("Assemble backrest ",b);
		super.check.add("Cut seat ");
	}
}
