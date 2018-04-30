import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {

	public MyHandler() {
		// TODO Auto-generated constructor stub
	}

	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		System.out.println(arg1.getReturnType().getName()+" "+arg1.getName()+" ");
		for(Object o:arg2)
			System.out.print(o.getClass().getName()+" "+o.toString()+" ");
		System.out.println();
		return 12.3;
	}

}
