import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Farmacist;
import classes.Medicament;
import interfaces.*;
import interfacesch.*;

public class LoginListener implements ActionListener {
private Main m;
private JTextField farmacist_name,farmacist_pass;
	public LoginListener(Main m,JTextField farmacist_name,JTextField farmacist_pass) {
		super();
		this.m=m;
		this.farmacist_name=farmacist_name;
		this.farmacist_pass=farmacist_pass;
	}

	public void actionPerformed(ActionEvent arg0) {
		for(Server s:Connection.getServ_list())
        {try {
		String farm="DBManage-"+s.getDbase();
		Registry registry = LocateRegistry.getRegistry(s.getHost());
        DBManageinter dbm=(DBManageinter)registry.lookup(farm);
        Farmacist f=dbm.getFarmacist(farmacist_name.getText());
        System.out.println(farmacist_name.getText());
        if(f!=null)
        {m.farmacie=dbm.getFarmacieId(f.getIDFarmacie());
        	System.out.println(f.getNume()+" "+f.getParola());
        if(f.getParola().equals(farmacist_pass.getText()))
        	{m.serv=s;
        	m.db=dbm;
        	m.fi=(Farmacieinter)registry.lookup("Farmacie-"+s.getDbase());
        	m.mfi=(Med_Farmacieinter)registry.lookup("Med_Farm-"+s.getDbase());
        	m.mi=(Medicamentinter)registry.lookup("Medicament-"+s.getDbase());
            m.dbch=(DBManageinterch)registry.lookup("DBManagech-"+s.getDbase());;
            m.fich=(Farmacieinterch)registry.lookup("Farmaciech-"+s.getDbase());;
            m.mfich=(Med_Farmacieinterch)registry.lookup("Med_Farmch-"+s.getDbase());;
            m.mich=(Medicamentinterch)registry.lookup("Medicamentch-"+s.getDbase());;
        	m.build();
        	break;}
        }}
        catch (Exception e) {
            System.err.println("Connection Exception: ");
            e.printStackTrace();
        }
        m.label_resp.setText("Wrong user or pass");

	}}
}
