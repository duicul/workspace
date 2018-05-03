package brokerlib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class NetworkCommunication {
	public NetworkCommunication() {}
	
	public static Object read(InputStream is,String o) throws IOException
	{System.out.print("unmarshall "+o);
	if(o.equals(int.class.getName())||o.equals(Integer.class.getName()))
	{byte[] b=new byte[Integer.BYTES];
	is.read(b,0,Integer.BYTES);
	int len=ByteBuffer.wrap(b).getInt();
	System.out.println(" "+len);	
	return len;}
	if(o.equals(String.class.getName()))
	{byte[] b=new byte[Integer.BYTES];
	is.read(b,0,Integer.BYTES);
	int len=ByteBuffer.wrap(b).getInt();
		byte[] str=new byte[len];
		is.read(str);
		System.out.println(" "+len+" "+new String(str));
		return new String(str);}
	if(o.equals(float.class.getName())||o.equals(Float.class.getName()))
	{byte[] b=new byte[Float.BYTES];
	is.read(b,0,Float.BYTES);
	float len=ByteBuffer.wrap(b).getFloat();	
	System.out.println(" "+len);	
	return len;}
	return null;}
	
	public static void write(Object o,OutputStream os) throws IOException
	{byte[] q=NetworkCommunication.marshall(o);
	System.out.println((os==null)+q.toString()+" "+q.length);	
	os.write(q);}
		
	public static byte[] marshall(Object o)
	{System.out.println("marshalling "+o.toString());
		if(o.getClass().getName().equals(int.class.getName()))
		{System.out.println("int-"+o.toString());
		return ByteBuffer.allocate(Integer.BYTES).putInt((Integer)o).array();}
	if(o.getClass().getName().equals(Integer.class.getName()))
	{System.out.println("integer-"+o.toString());
	return ByteBuffer.allocate(Integer.BYTES).putInt((Integer)o).array();}
	if(o.getClass().getName().equals(String.class.getName()))
	{System.out.println("string-"+o.toString());
	String s=(String)o;
		ByteBuffer n=ByteBuffer.allocate(Integer.BYTES+s.length());
		n.putInt(s.length());
	n.put(s.getBytes(Charset.forName("UTF-8")));
	return n.array();}
	if(o.getClass().getName().equals(float.class.getName()))
	{System.out.println("float"+o.toString());return ByteBuffer.allocate(Float.BYTES).putFloat((Float)o).array();}
	if(o.getClass().getName().equals(Float.class.getName()))
	{System.out.println("Float"+o.toString());return ByteBuffer.allocate(Float.BYTES).putFloat((Float)o).array();}
	System.out.println("null"+o.toString());
	return null;	
	}	
}
