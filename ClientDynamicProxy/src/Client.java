import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

	public Client() {
		
	}
    public static void main(String args[])
    {InvocationHandler handler = new MyHandler();
	MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
            MyInterface.class.getClassLoader(),
            new Class[] { MyInterface.class },
            handler);
    proxy.calc((float)12.3, 11, "ana");
    proxy.retur(12, 13);
    proxy.retur1("maria", 66);
    }
}
