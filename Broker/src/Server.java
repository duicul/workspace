import brokerlib.Naming;

public class Server{

	public Server() {}
	
	public static void main(String args[])
	{/*ByteBuffer q=ByteBuffer.allocate(Integer.BYTES*2).putInt(3).putInt(6);
	ByteBuffer t=ByteBuffer.wrap(q.array());
	System.out.println(t.getInt()+" "+t.getInt());
    String s="ana";
    Object o=(Object)s;
    System.out.println(o.getClass().getName()+" "+o.getClass().cast(o));*/
	try{Naming r=Naming.registry("localhost");
	r.postObject(new SomeReal(), "test");
	Thread.sleep(1000);
	}catch(Exception e)
	{e.printStackTrace();}
	}

}
