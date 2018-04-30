import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import classes.Med_Farmacie;

public class BuyListener implements ActionListener {
private Main m;
	public BuyListener(Main m) {
		super();
		this.m=m;
	}

	public void actionPerformed(ActionEvent arg0) {
		for(SoldProduct slp:m.sp)
		{try {
			Med_Farmacie stoc=m.mi.getMed_Farm(slp.m.getID(),m.farmacie.getID());
		    
			m.mfich.changeQuantity(stoc, stoc.getCantitate()-slp.cant);
			System.out.println(slp.m.getNume()+" "+slp.m.getPret()+"x"+slp.cant);
		
		} catch (RemoteException e) {
			e.printStackTrace();}
		}
		System.out.println(m.total);
	  
	  m.sp.removeAll(m.sp);
	  m.calc_total();
	  m.build();
	}

}
