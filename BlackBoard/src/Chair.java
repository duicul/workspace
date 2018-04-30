import java.util.*;


public class Chair {
	protected String s;
	protected List<KS> c;
	protected String comp="";
	public Chair(List<KS> ks)
	{this.s=new String("Chair ");
	this.c=ks;
	this.comp=this.s;
	for(KS i:ks)
		comp+=i;
	//System.out.println("comp "+comp);
	}
	
	public boolean check(List<String> c,String val)
	{boolean aux=!s.matches("(.*)"+val+"(.*)");
	for(String st:c)
		aux=aux&&s.matches("(.*)"+st+"(.*)");
	//System.out.println(val+"  "+aux+" "+(comp.matches("(.*)"+val+"(.*)"))+"  "+s);
		return aux&&(comp.matches("(.*)"+val+"(.*)"));}
	
	public boolean complete()
	{for(KS i:c)
	 {//System.out.println(s+"  "+i+"  "+s.matches("(.*)"+i+"(.*)"));
		if(!s.matches("(.*)"+i+"(.*)"))
			 return false;}
	return true;}
	
	public void addString(String s)
	{this.s+=s;}
	public String toString()
	{return this.s;}
}
