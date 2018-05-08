import java.lang.reflect.UndeclaredThrowableException;

import brokerlib.Naming;
import info.Info;

public class Client {

	public Client() {}
	
  public static void main(String args[])
  {int a=4;
  Object o=a;
	  System.out.println(o.getClass().getName());
	  
	try{Naming r=Naming.registry("localhost");
	Info i=(Info) r.getStub("info");
	math.Math m =(math.Math) r.getStub("math");
	System.out.println("\n"+i.get_temp("Timisoara")+"\n");
	System.out.println("\n"+i.getroad_info(45)+"\n");
	System.out.println("\n"+m.do_add((float)12.7, (float)11.3)+"\n");
	System.out.println("\n"+m.do_sqrt((float)64)+"\n");
	}
	catch (UndeclaredThrowableException ute) {
		  try {
			throw ute.getCause();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		}
	catch(Exception e)
	{e.printStackTrace();}
	
  }
  
}
