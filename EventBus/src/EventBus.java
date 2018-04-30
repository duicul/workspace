import java.util.*;


public class EventBus {
     List<Subscriber> s=new ArrayList<Subscriber>();
     List<String> st=new ArrayList<String>();
	public EventBus() {
		// TODO Auto-generated constructor stub
	}
	
	public void publish(Event e)
	{int i;
	if(e.getMess().equals("Finish"))
	{System.out.println(e.getChair());
	return;}
	for(i=0;i<s.size();i++)
	{//System.out.println(e.getMess()+" "+st.get(i)+st.get(i).equals(e.getMess())+"|");
	if(st.get(i).equals(e.getMess()))
		{s.get(i).inform(e);
	    /*System.out.println(e.getMess());*/}}
	}
	
	public void subscribe(String evtype,Subscriber s)
	{this.s.add(s);
	this.st.add(evtype);}

}
