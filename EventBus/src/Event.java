
public class Event {
    private String s;
    private Chair c;
	public Event(Chair c,String s) {
		this.c=c;
		this.s=s;
	}
	
	public Chair getChair()
	{return c;}
		
	public String getChairInfo()
	{return c.toString();}
	
	public String getMess()
	{return this.s;}
	

}
