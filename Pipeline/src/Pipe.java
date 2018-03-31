import java.util.*;


public class Pipe {
 private List<Filter> f;
	public Pipe()
	{this.f=new ArrayList<Filter>();}
	public Pipe(List<Filter> f)
	{this.f=f;}
	public void addFilter(Filter f)
	{this.f.add(f);}
    public Chair process(Chair c)
    {for(Filter i:f)
     i.process(c);	
    	return c;}
    public void clearFilter()
    {this.f.clear();}
}
