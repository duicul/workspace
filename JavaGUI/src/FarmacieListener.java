import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import classes.Farmacie;
public class FarmacieListener implements ActionListener {
private Farmacie f;
public FarmacieListener(Farmacie f)
{this.f=f;}
	public void actionPerformed(ActionEvent e) {
		try {
			System.out.println(f.getNume());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

}
