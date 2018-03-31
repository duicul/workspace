import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.io.*;
import classes.*;
import interfaces.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	private JFrame frame = new JFrame("Alba");
	private JLabel label1= new JLabel("Alba"),label2= new JLabel("Neagra"),label3= new JLabel("");
	private boolean press=false;
	private String mess;
	private JPanel cp1,cp2,cp3;
	private JTextField tf=new JTextField(10);
	private JTextField tfhost=new JTextField(15),tfdbase=new JTextField(15);;
    private JButton button = new JButton("Press"),buttonlogin = new JButton("Login");
    private JButton[] buttons=new JButton[100];
    private int butcount=0;
    private List<Farmacie> fl;
    public Main() {
		// TODO Auto-generated constructor stub
	}
	
	public JPanel getFarmacie(String host,String dbase)
	{JPanel farmpan=new JPanel();
	try {
        String name1 = "DB-"+dbase;
        String name2 = "Farmacie-"+dbase;
        String name3 = "Stoc-"+dbase;
        String name4 = "Produs-"+dbase;
        Registry registry = LocateRegistry.getRegistry(host);
        DBManageinter db = (DBManageinter) registry.lookup(name1);
        //Farmacieinter farm = (Farmacieinter) registry.lookup(name2);
        //Stocinter stoc = (Stocinter) registry.lookup(name3);
        //Produsinter prod = (Produsinter) registry.lookup(name4);
        fl=db.getFarmacii();

    for(Farmacie f:fl)
	{buttons[butcount]=new JButton(f.getNume()+" "+f.getAdresa()+" "+f.getOras());
	buttons[butcount].addActionListener(new FarmacieListener(f));
    	farmpan.add(buttons[butcount++]);
	}
     } catch (Exception e) {
        System.err.println("Connection Exception: ");
        e.printStackTrace();
    }
	
	return farmpan;
	}
	
    public void app()
    {cp1=new JPanel();
    cp2=new JPanel();
    cp3=new JPanel();
    cp1.add(label1);
    cp2.add(label2);
    cp3.add(label3);
    tf.setSize(100, 400);
    JPanel start=new JPanel();
    start.setLayout(new GridLayout(3,1));
    start.add(new JLabel("Enter Host "));
    start.add(tfhost);
    start.add(new JLabel("Enter DataBase "));
    start.add(tfdbase);
    start.add(buttonlogin);
    start.setLayout(new GridLayout(3,2));
    frame.add(start);
    frame.add(tf);
    frame.add(cp3);
		frame.add(cp1);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       //frame.setLayout(new FlowLayout()/*GridLayout(3,1)*/);
	       //frame.setSize(500,500);
	       buttonlogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
			System.out.println("Host: "+tfhost.getText());
			System.out.println("Data Base: "+tfdbase.getText());
			frame.add(new Main().getFarmacie(tfhost.getText(),tfdbase.getText()));
			frame.revalidate();
            frame.pack();
			}
	       });
	       //button.setSize(50, 50);
	       button.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	             frame.getContentPane().removeAll();
	             frame.add(button);
	             frame.add(tf);
	             frame.add(cp3);
	        	   try {int a=Integer.parseInt(tf.getText());
	        		   label3.setText(String.valueOf(Math.pow(a,2)));
	        	   }
	        	   catch(Exception ex)
	        	   {label3.setText("Nan");}
	        	   if(press)
	            	 {frame.remove(cp1);frame.add(cp2);}
	                 else {frame.add(cp1);frame.remove(cp2);}
	        	   //mess=press?"Alba":"Neagra";
	        	   //frame.add(button);
	        	 frame.setLayout(new FlowLayout());
	             frame.revalidate();
	             frame.pack();
	        	   press=!press;
	        	   System.out.println("Button pressed:)");
	           }});
	       frame.add(button); // Adds Button to content pane of frame
	       frame.setLayout(new FlowLayout());
	       System.out.println("Components: "+frame.getComponentCount());
	       frame.setVisible(true);
	       frame.pack();}
	public static void main(String[] args) {
		new Main().app();

	}

}
