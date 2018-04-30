import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RemoveSold implements ActionListener {
private Main m;
private SoldProduct sp;
	public RemoveSold(Main m,SoldProduct sp) {
		super();
		this.m=m;
		this.sp=sp;
	}

	public void actionPerformed(ActionEvent e) {
		m.sp.remove(sp);
		m.calc_total();
	}

}
