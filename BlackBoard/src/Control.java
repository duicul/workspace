import java.util.*;


public class Control {
    private List<KS> c;
    private BlackBoard b;
	public Control(BlackBoard b)
	{this.c=new ArrayList<KS>();
	 this.b=b;
	 this.c.add(new KSB(b));
	 this.c.add(new KSC(b));
	 this.c.add(new KSF(b));
	 this.c.add(new KSPnb(b));
	 this.c.add(new KSPfull(b));
	 this.c.add(new KSS(b));}
    public void addKS(KS c)
    {this.c.add(c);}
    
	
	public void loop()
	{while(!b.checkSol())
	{this.nextSource();}
	}
    
    public void nextSource()
    {KS aux=null;
    if(b.inspect().complete())
    	{b.update();
    	return;}
    	for(KS i:c)
    	if(i.execCondition())
    		{aux=i;
    	    break;}
     if(aux!=null)
    	aux.execAction();
     //System.out.println(b.inspect());    
    }
}
