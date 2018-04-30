import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;


public class Loop {
private String path;
private List<Class<?>> t=new ArrayList<Class<?>>();
public Loop(String path) {
		this.path=path;
	}
    public static boolean contain(List<Class<?>> l,Class<?> cls)
    {for(Class<?> c:l)
    	{//System.out.println("list"+c.getName()+"  "+cls.getName()+" "+c.equals(cls));
    	if(c.getName().equals(cls.getName()))
    		return true;}
    return false;}
    
    
    public void run()
	{URLClassLoader cls;
	try {
		File f=new File(path);
		cls=new URLClassLoader(new URL[] {f.toURI().toURL()});	
		System.out.println("Scanning...");
		
		if(f.isDirectory())
		for(File i:f.listFiles())
		{if(i.getName().contains(".class"))
			{Class<?> aux=cls.loadClass(i.getName().replace(".class", ""));
			if(!Loop.contain(t, aux))
			{t.add(cls.loadClass((i.getName().replace(".class", ""))));
			System.out.println();
			System.out.println("Added"+i.getPath()+"  "+i+" \n "+i.getAbsolutePath()+"  "+i.getName());
			Loop.testClass(aux);
			}
			}
        }
		else System.out.println("Not a directory");
		
		System.out.println();
		cls.close();
	}
	catch(Exception e)
	{e.printStackTrace();}
	}
    
    
    public static void testClass(Class<?> c)
    {Random r=new Random();
    
    try {
    System.out.println(c.getName());
	Constructor<?> con=c.getConstructor(new Class<?>[]{});
	System.out.println(c.getName()+" "+con.getName()+" "+con.getParameterCount());
	
	Object aux=con.newInstance(new Object[]{});
	Method m=c.getMethod("toString");
	Object o=m.invoke(aux,new Object[]{});
	
	System.out.println("Fields");
	for(Field f:c.getDeclaredFields())
		System.out.println(f.getName()+" "+f.getModifiers()+" "+f.getType().getName()+" "+f.isAccessible());
   
   
	System.out.println();
	System.out.println("Methods");
	for(Method i:c.getDeclaredMethods()){
		System.out.print(Modifier.toString(i.getModifiers())+" "+i.getName()+" "+i.getParameterCount()+" parameter(s) : ");
		for(Parameter p:i.getParameters())
			System.out.print(p.getType()+":"+p.getName()+" ");	
		System.out.println();
	}
	
	
	System.out.println("Test methods:");
	for(Method i:c.getDeclaredMethods()){
		
		if(!Modifier.toString(i.getModifiers()).equals("private"))
		{System.out.println("Test "+i.getName());
		try{
			
	    if(i.getParameterCount()==0)
		{Object ret=i.invoke(aux, new Object[]{});
		System.out.println(i.getName()+"()="+ret.getClass().cast(ret));}
		
		if(i.getParameterCount()==1&&(i.getParameterTypes()[0]==int.class||i.getParameterTypes()[0]==Integer.class))
		{int randval=r.nextInt(1000);
		Object ret=i.invoke(aux, new Object[]{new Integer(randval)});
			System.out.println(i.getName()+"("+randval+")="+ret.getClass().cast(ret));}
		
		if(i.getParameterCount()==2)
		{boolean test=true;
			for(Class<?> cv:i.getParameterTypes())
		    test=test&&(cv.equals(int.class)||cv.equals(Integer.class));
		if(test)
		{int randval1=r.nextInt(1000),randval2=r.nextInt(1000);
			Object ret=i.invoke(aux, new Object[]{new Integer(randval1),new Integer(randval2)});
		System.out.println(i.getName()+"("+randval1+","+randval2+")="+ret.getClass().cast(ret));}}
	   
		}
		catch(Exception e)
		{System.out.println("Exception "+Modifier.toString(i.getModifiers())+" "+i.getName());}
		}
	}
	
	
	if(o.getClass().hashCode()==String.class.hashCode())
		System.out.println(c.getName()+" says toString "+(String)o);
	int val=r.nextInt(1000);
	Method proc=c.getMethod("process",new Class<?>[]{int.class});
	Object ret=proc.invoke(aux, new Object[]{new Integer(val)});
	System.out.println("process("+val+")="+ret.getClass().cast(ret));
	}
	catch(Exception e)
	{e.printStackTrace();}
	}
    
	public void showClasses()
	{
	for(Class<?> c:t)
	{try {
		System.out.println(c.getName());
	Constructor<?> con=c.getConstructor(new Class<?>[]{});
	System.out.println(c.getName()+" "+con.getName()+" "+con.getParameterCount());
	Object aux=con.newInstance(new Object[]{});
	Method m=c.getMethod("toString");
	Object o=m.invoke(aux,new Object[]{});
	if(o.getClass().hashCode()==String.class.hashCode())
		System.out.println("toString "+(String)o);
	Method proc=c.getMethod("process",new Class<?>[]{int.class});
	Object ret=proc.invoke(aux, new Object[]{new Integer(1)});
	System.out.println(ret.getClass().cast(ret));
	}
	catch(Exception e)
	{e.printStackTrace();}
	}}
	
}
