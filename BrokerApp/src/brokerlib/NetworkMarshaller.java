package brokerlib;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkMarshaller implements InvocationHandler{
private String host,name;
private int port;
	public NetworkMarshaller(String host,int port,String name) {
		this.host=host;
		this.port=port;
		this.name=name;
	}

	public Object invoke(Object proxy, Method m, Object[] arg) throws Throwable {
		Object ret = null;
		String ret_type=null;
		Socket s=new Socket(InetAddress.getByName(host),port);
		OutputStream os=s.getOutputStream();
		InputStream is=s.getInputStream();
		NetworkCommunication.write("call", os);
		NetworkCommunication.write(name, os);
		NetworkCommunication.write(m.getName(), os);
		NetworkCommunication.write(arg.length, os);
		
		Class<?>[] params=m.getParameterTypes();
		for(int i=0;i<arg.length;i++)
		{NetworkCommunication.write(params[i].getName(), os);
		System.out.println("proxy "+params[i].getName()+" "+arg[i]);
		NetworkCommunication.write(arg[i], os);}
		ret_type=(String) NetworkCommunication.read(is,String.class.getName());
		ret=NetworkCommunication.read(is,ret_type);
		System.out.println("method_ret_type: "+m.getReturnType().getName()+" "+Class.forName(ret_type).cast(ret));
		s.close();
		return ret;
	}

}
