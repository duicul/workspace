import java.util.ArrayList;
import java.util.List;
import classes.*;
import interfaces.*;
import interfacesch.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class Main {
	public  double total=0;
	public Server serv=null;
	public Farmacie farmacie=null;
	public List<SoldProduct> sp;
	public List<JTextField> quantities;
	public JFrame frame = new JFrame("Alba");
	public JLabel label_resp= new JLabel();
	public JLabel total_price= new JLabel("Total:     \n");
	public JPanel products_panel= new JPanel();
	public JPanel left_total,main_opp;
	private JTextField farmacist_name=new JTextField(15);
	private JPasswordField farmacist_pass=new JPasswordField(15);
	private JPanel jpl=new JPanel();
	private JScrollPane jsp=new JScrollPane(jpl);
	private JTextField search_field=new JTextField(20);
	public DBManageinter db;
	public Farmacieinter fi;
	public Med_Farmacieinter mfi;
	public Medicamentinter mi;
	public DBManageinterch dbch;
	public Farmacieinterch fich;
	public Med_Farmacieinterch mfich;
	public Medicamentinterch mich;
	
    public Main() {
    left_total=new JPanel(new GridLayout(2,1));
    left_total.add(total_price);
    left_total.add(this.products_panel);
    frame.setLayout(new GridLayout(1,3));
	}

    public void calc_total()
    {this.products_panel=new JPanel();
    total=0;
	for(SoldProduct slp:sp)
	{System.out.println(slp.m.getNume());
	JPanel aux=new JPanel();
	JButton remove=new JButton("X");
	remove.addActionListener(new RemoveSold(this,slp));
	JLabel med_sold=new JLabel(slp.m.getNume()+" "+slp.cant+"x"+slp.m.getPret()+"="+slp.cant*slp.m.getPret());
	aux.add(remove);
	aux.add(med_sold);
	this.products_panel.add(aux);
	total+=slp.m.getPret()*slp.cant;
	}
	products_panel.revalidate();
	products_panel.repaint();
	products_panel.setLayout(new GridLayout(sp.size(),1));
	total_price= new JLabel("Total: "+total);
	left_total.removeAll();
	left_total.add(total_price);
	left_total.add(products_panel);
	left_total.revalidate();
	left_total.repaint();
    this.frame.pack();
    this.search(jpl, search_field, this);
    }
    
    public void search(JPanel jpl,JTextField search_field,Main m)
    {//Put results in jpl
    //Search by text in search_field
    //m -> object app
    jpl.removeAll();
    int found =0;
	try {
	List<Medicament> ml=m.fi.getMedicamentsFarmacie(m.farmacie);
	jpl.setLayout(new GridLayout(ml.size(),1));
    //System.out.println(m.farmacie.getNume()+" search "+ml.size()+" "+search_field.getText());
	for(Medicament med:ml)
    {//System.out.println("medicament: "+med.getNume());
	if((med.getNume().matches("(.*)"+search_field.getText()+"(.*)"))||search_field.getText().equals(med.getID()))
    {boolean bought=false;
    	for(SoldProduct slp:m.sp)
    if(slp.m.getID()==med.getID())
    	bought=true;
    if(!bought)
    {JPanel aux=new JPanel();
    Med_Farmacie mf=m.mi.getMed_Farm(med.getID(), m.farmacie.getID());
    JLabel medname=new JLabel(med.getNume()+" "+med.getPret()+" "+mf.getCantitate());
    JTextField quant=new JTextField(5);
    JButton but=new JButton("+");
    but.addActionListener(new TotalChange(m,med,"-",quant,mf));
    aux.add(medname);
    aux.add(quant);
    aux.add(but);
    jpl.add(aux);
    found++;}
    }}}
    catch(Exception e)
    {e.printStackTrace();}
	//search_field.setText("");
	jpl.setLayout(new GridLayout(found+1,1));
	jpl.setMinimumSize(new Dimension(100,100));
	jpl.revalidate();
	jpl.repaint();
	m.frame.pack();}
    
    public void build()
    {JPanel search_medicine=new JPanel();
    search_medicine.setLayout(new GridLayout(2,1));
    JPanel search_menu=new JPanel();
    JButton search_button=new JButton("Search");
    JButton log_out=new JButton("Log out");
    log_out.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			begin();			
		}});
    search_menu.add(search_field);
    search_menu.add(search_button);
    search_button.addActionListener(new SearchListener(this,search_field,jpl));
    search_medicine.add(search_menu);
    search_medicine.add(jpl);
    JButton buy=new JButton("Buy");
    buy.addActionListener(new BuyListener(this));
    this.frame.getContentPane().removeAll();
    this.frame.setLayout(new GridLayout(1,4));
    this.frame.add(log_out);
    this.frame.add(this.left_total);
	this.frame.add(search_medicine);
	this.frame.add(buy);
    this.frame.setVisible(true);
    this.calc_total();
    this.frame.pack();}
    
	public void begin() {
	jpl=new JPanel();
	total=0;
	this.quantities=new ArrayList<JTextField>();
	this.sp=new ArrayList<SoldProduct>();
	 this.search_field.setText("");
	 main_opp=new JPanel();
	 main_opp.setLayout(new GridLayout(3,2));
	 main_opp.add(new JLabel("User name"));
	 main_opp.add(farmacist_name);
	 main_opp.add(new JLabel("Password"));
	 main_opp.add(farmacist_pass);
	 main_opp.add(label_resp);
	  JButton ok_login=new JButton("Login");
	  ok_login.addActionListener(new LoginListener(this,farmacist_name,farmacist_pass));
	  main_opp.add(ok_login);
	  farmacist_name.setText("");
	  farmacist_pass.setText("");
	  this.frame.getContentPane().removeAll();
	  frame.add(main_opp);
	  frame.setVisible(true);
	  frame.pack();
	}
	
	public static void main(String[] args) {
		Main m=new Main();
		m.begin();
		
	}

}
