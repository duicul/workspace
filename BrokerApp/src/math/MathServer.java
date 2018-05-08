package math;
import brokerlib.Naming;

public class MathServer{

	public MathServer() {}
	
	public static void main(String args[])
	{
	try{Naming r=Naming.registry("localhost");
	r.postObject(new MathReal(), "math");
	Thread.sleep(1000);
	}catch(Exception e)
	{e.printStackTrace();}
	}

}
