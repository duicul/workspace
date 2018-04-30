
public class Subscriber {
    protected String s;
    private Event gen;
    private EventBus e;
	public Subscriber(EventBus e,String s,Event gen) {
		this.s=s;
		this.gen=gen;
		this.e=e;
	}
	public void inform(Event e)
	{e.getChair().addString(s);
	this.e.publish(gen);
		
	}

}
