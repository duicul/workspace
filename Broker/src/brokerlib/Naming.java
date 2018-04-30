package brokerlib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Naming {
private String host;
private int port;
	private Naming(String host,int port) {
		this.host=host;
		this.port=port;	
		}
	
	public static Naming registry(String host,int port) throws NoRegistryException
	{Socket s;
	try{s=new Socket(InetAddress.getByName(host),port);
	OutputStream os=s.getOutputStream();
	InputStream is=s.getInputStream();
	NetworkCommunication.write("confirm", os);
	String mess=(String)NetworkCommunication.read(is,String.class.getName());
	if(mess.equals("OK"))
	{s.close();return new Naming(host,port);}
	}
	catch(Exception e){throw new NoRegistryException();}
	try {s.close();} catch (IOException e) {throw new NoRegistryException();}
	throw new NoRegistryException();
		
	}
	
	public static Naming registry(String host) throws NoRegistryException
	{return Naming.registry(host,2003);}

	public void postObject(Object o,String name) throws UnknownHostException, IOException, InterruptedException
	{new ProxySkel(o,host,port,name).start();
	Thread.sleep(1000);}
	
	
	public Object getStub(String name) throws IllegalArgumentException, ClassNotFoundException, UnknownHostException, IOException
	{Socket s=new Socket(InetAddress.getByName(host),port);
	OutputStream os=s.getOutputStream();
	InputStream is=s.getInputStream();
	NetworkCommunication.write("lookup", os);
	NetworkCommunication.write(name, os);
	String mess=(String) NetworkCommunication.read(is,String.class.getName());
	if(!mess.equals("OK"))
		{s.close();throw new ClassNotFoundException();}
	String class_name=(String) NetworkCommunication.read(is,String.class.getName());
	s.close();
	System.out.println("new stub "+class_name+" "+host+" "+port+" "+name);
	return Proxy.newProxyInstance(Class.forName(class_name).getClassLoader(),
            new Class[] { Class.forName(class_name) },
            new NetworkMarshaller(host,port,name));}
}
