
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;


public class SearchListener implements ActionListener {
private Main m;
private JTextField search_field;
private JPanel jpl;
	public SearchListener(Main m,JTextField search_field,JPanel jpl) {
		super();
		this.m=m;
		this.search_field=search_field;
		this.jpl=jpl;
	}

	public void actionPerformed(ActionEvent arg0) {
		m.search(jpl, search_field, m);

	}

}
