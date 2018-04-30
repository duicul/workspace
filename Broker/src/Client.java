import java.lang.reflect.UndeclaredThrowableException;

import brokerlib.Naming;

public class Client {

	public Client() {}
	
  public static void main(String args[])
  {int a=4;
  Object o=a;
	  System.out.println(o.getClass().getName());
	  
	try{Naming r=Naming.registry("localhost");
	Some m=(Some) r.getStub("test");
	System.out.println("result: "+m.add(10, 3));
	System.out.println("result "+m.add((float)10.3,(float)12.1));
	System.out.println("result "+m.sayhello("Mark"));
	/*System.out.println("result: "+m.add(9, 5));
	System.out.println("result: "+m.add(8, 7));
	System.out.println("result: "+m.add(7, 9));*/
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
