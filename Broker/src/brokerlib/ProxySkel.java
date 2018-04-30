package brokerlib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxySkel extends Thread {
private Object o;
private String reg_host,name;
private int reg_port;
	public ProxySkel(Object o,String reg_host,int reg_port,String name) {
		this.o=o;
		this.reg_host=reg_host;
		this.reg_port=reg_port;
		this.name=name;
	}
    
	public void invoke(InputStream is,OutputStream os)
	{try {
	String method=(String) NetworkCommunication.read(is,String.class.getName());
	System.out.println("skel "+method);
	int nr_param=(Integer) NetworkCommunication.read(is,int.class.getName());
	String[] params=new String[nr_param];
	Object[] params_val=new Object[nr_param];
	for(int i=0;i<nr_param;i++)
	{params[i]=(String) NetworkCommunication.read(is,String.class.getName());
	params_val[i]=NetworkCommunication.read(is,params[i]);
	System.out.println("skel "+params[i]+" "+params_val.toString());}
	//Method m=o.getClass().getMethod(method,params);
	Method meth=null;
	for(Method m:o.getClass().getDeclaredMethods())
	{if(m.getName().equals(method)&&m.getParameterCount()==nr_param)
		{Class<?>[] param_classes=m.getParameterTypes();
		boolean correct=true;
		for(int q=0;q<param_classes.length;q++)
			if(!param_classes[q].getName().equals(params[q]))
			{correct=false;break;}
		if(correct)
			{meth=m;break;}
		}}
	if(meth!=null)
	{Object ret=meth.invoke(o,params_val);
	NetworkCommunication.write(ret.getClass().getName(), os);
	NetworkCommunication.write(ret, os);}
	}catch(Exception e)
	{e.printStackTrace();}
	}
	
	public void run() {
		Socket s1 = null;
		ServerSocket ss;
		try{s1=new Socket(this.reg_host,this.reg_port);
		OutputStream os1=s1.getOutputStream();
		InputStream is1=s1.getInputStream();
		NetworkCommunication.write("post", os1);
		NetworkCommunication.write(name, os1);
		Class<?> clas = null;
		boolean found=false;
		for(Class<?> inter:o.getClass().getInterfaces())
		{for(Class<?> superinter:inter.getInterfaces())	
		if(superinter.getName().equals(External.class.getName()))
				{System.out.println("interfaces: "+inter.getName()+" extends "+superinter.getName());
				clas=inter;found=true;break;}
		}
		if(!found)
			{s1.close();throw new ClassNotFoundException();}
		NetworkCommunication.write(clas.getName(), os1);
		NetworkCommunication.write(InetAddress.getLocalHost().getHostAddress(), os1);
		ss=new ServerSocket(0);
		NetworkCommunication.write(ss.getLocalPort(), os1);
		String mess=(String)NetworkCommunication.read(is1, String.class.getName());
		s1.close();
		System.out.println("proxy skel mess "+mess);
		if(!(mess).equals("OK"))
		return;
		}
		catch(Exception e)
		{e.printStackTrace();
		return;}
		finally {try {s1.close();}
		catch (IOException e) {e.printStackTrace();}}
		System.out.println("proxy skel listenning ");
		while(true)
	{try {
		Socket s=ss.accept();
		System.out.println("proxy skel accept "+s.getPort()+" "+s.getInetAddress().getHostAddress());
		InputStream is=s.getInputStream();
		OutputStream os=s.getOutputStream();
		this.invoke(is,os);
		
		
	}
		catch(Exception e)
		{e.printStackTrace();return;}
	    
	/*finally{try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}*/
	
	}
		}

}
