package brokerregistry;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import brokerlib.NetworkCommunication;

public class Registry {
private List<Server> servs;
private int port;

public Registry() {
	servs=new LinkedList<Server>(); 
	this.port=2003;}

public Registry(int port) {
		servs=new LinkedList<Server>(); 
		this.port=port;
	}
public void addServer(Server e)
{for(Server s:servs)
	if(s.name.equals(e.name))
		{System.out.println("remove "+e.name);servs.remove(s);}
 servs.add(e);
}

public Server getServer(String name)
{for(Server s:servs)
	if(s.name.equals(name))
		return s;
return null;}


public void post(InputStream is,OutputStream os)
{System.out.println("post");
String host1,name1,clas1;
int port1;
try {
name1=(String) NetworkCommunication.read(is, String.class.getName());
clas1=(String) NetworkCommunication.read(is,String.class.getName());
host1=(String) NetworkCommunication.read(is,String.class.getName());
port1=(Integer) NetworkCommunication.read(is,int.class.getName());
this.addServer(new Server(host1,port1,name1,clas1));
System.out.println("post "+name1+" "+clas1+" "+host1+" "+port1);
NetworkCommunication.write("OK", os);
} catch (IOException e) {e.printStackTrace();
try {NetworkCommunication.write("ERROR", os);}
catch (IOException e1) {
	e1.printStackTrace();
}}}

public void lookup(InputStream is,OutputStream os)
{System.out.println("lookup");
	String name1;
try {
name1=(String) NetworkCommunication.read(is, String.class.getName());
System.out.println("lookup "+name1);
Server looked=this.getServer(name1);
NetworkCommunication.write("OK", os);
NetworkCommunication.write(looked.clas, os);
} catch (IOException e) {e.printStackTrace();
try {NetworkCommunication.write("ERROR", os);}
catch (IOException e1) {
	e1.printStackTrace();
}}}

public void call(InputStream is,OutputStream os)
{System.out.println("call");
	String name1;
	try {name1=(String) NetworkCommunication.read(is, String.class.getName());
	System.out.println("call"+name1);
	Server ser=this.getServer(name1);
	Socket s=new Socket(InetAddress.getByName(ser.host),ser.port);
	InputStream sis=s.getInputStream();
	OutputStream sos=s.getOutputStream();
	
	String method_name=(String) NetworkCommunication.read(is, String.class.getName());
	int param_num=(Integer) NetworkCommunication.read(is, int.class.getName());
	System.out.println("param_num "+param_num);
	String[] class_name=new String[param_num];
	Object[] params_val=new Object[param_num];
	for(int i=0;i<param_num;i++)
	{class_name[i]=(String) NetworkCommunication.read(is, String.class.getName());
	System.out.println("param_class_name"+ class_name[i]);
	params_val[i]=NetworkCommunication.read(is,class_name[i]);
	System.out.println("param_val "+ params_val[i]);
	}
	
	NetworkCommunication.write(method_name, sos);
	NetworkCommunication.write(param_num, sos);
	for(int i=0;i<param_num;i++)
	{NetworkCommunication.write(class_name[i], sos);
	NetworkCommunication.write(params_val[i], sos);
	}
	
	String ret_class=(String) NetworkCommunication.read(sis, String.class.getName());
	Object ret_val=NetworkCommunication.read(sis,ret_class);
	System.out.println("regserv_ret "+ret_class+" "+Class.forName(ret_class).cast(ret_val));
	
	NetworkCommunication.write(ret_class, os);
	NetworkCommunication.write(ret_val,os);
    s.close();
} catch (Exception e) {e.printStackTrace();
	try {NetworkCommunication.write("ERROR", os);}
	catch (IOException e1) {
		e1.printStackTrace();
	}}
	
}

	public static void main(String args[])
	{Registry r=new Registry();
		ServerSocket ss = null;
		try{ss=new ServerSocket(r.port);}
		catch(Exception e)
		{e.printStackTrace();
		try {
			ss.close();
		} catch (IOException e1) {e1.printStackTrace();}
		return;}	
		System.out.println("Listening.."+r.port);
		Socket s = null;
		while(true)
	{try {
		s=ss.accept();
		System.out.println("connecting..");
		InputStream is=s.getInputStream();
		OutputStream os=s.getOutputStream();
		String opt=(String) NetworkCommunication.read(is,String.class.getName());
		System.out.println("opt"+opt);
		if(opt.equals("confirm"))
		NetworkCommunication.write("OK", os);	
		else if(opt.equals("post"))
		r.post(is,os);
		else if(opt.equals("lookup"))
		r.lookup(is,os);
		else if(opt.equals("call"))
		r.call(is,os);
		}
		catch(Exception e)
		{e.printStackTrace();
		return ;}
	    finally{try {s.close();} catch (IOException e) {
				e.printStackTrace();}}
	
	}
	//ss.close();
		
		
	}
	
}
