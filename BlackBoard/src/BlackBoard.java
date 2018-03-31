import java.util.*;


public class BlackBoard {
 List<Chair> c;
 List<Chair> cc;
 List<KS> know;
	public BlackBoard() {
		this.c=new ArrayList<Chair>();
		this.cc=new ArrayList<Chair>();
		this.know=new ArrayList<KS>();
		this.know.add(new KSB(this));
		this.know.add(new KSC(this));
		this.know.add(new KSF(this));
		this.know.add(new KSPnb(this));
		this.know.add(new KSPfull(this));
		this.know.add(new KSS(this));}
	public Chair inspect()
	{return c.get(0);}
	
	public void addChair(List<Chair> c)
	{this.c.addAll(c);}
	
	public void addChair(Chair c)
	{this.c.add(c);}
	
	public void update()
	{Chair aux=c.get(0);
	 c.remove(0);
	 cc.add(aux);
	 System.out.println("\nUpdate\n");}
	
	public boolean checkSol()
	{return c.size()==0;}
    
	public String toString()
	{String aux="";
	for(Chair c:cc)
		aux+=c+"\n";
	return aux;}
}
