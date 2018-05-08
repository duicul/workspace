package info;
import brokerlib.Naming;

public class InfoServer{

	public InfoServer() {}
	
	public static void main(String args[])
	{try{Naming r=Naming.registry("localhost");
	r.postObject(new InfoReal(), "info");
	Thread.sleep(1000);
	}catch(Exception e)
	{e.printStackTrace();}
	}

}
