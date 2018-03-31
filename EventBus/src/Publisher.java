
public class Publisher {

	public Publisher() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Chair cH=new Chair();
		EventBus e =new EventBus();
		Event e1=new Event(cH,"Finish");
		Event e2=new Event(cH,"Need_C");
		Event e3=new Event(cH,"Need_F");
		Event e4=new Event(cH,"Need_B");
		Event e5=new Event(cH,"Need_S");
		Event e6=new Event(cH,"Need_P");
		Subscriber st=new Subscriber(e,"",e2);
		Subscriber c=new Subscriber(e,"Cut seat ",e3);
		Subscriber f=new Subscriber(e,"Assemble feet ",e4);
		Subscriber b=new Subscriber(e,"Assemble backrest ",e5);
		Subscriber s=new Subscriber(e,"Assemble stabilizer bar ",e6);
		Subscriber p=new Subscriber(e,"Package chair ",e1);
		e.subscribe(e2.getMess(), c);
		e.subscribe(e3.getMess(), f);
		e.subscribe(e4.getMess(), b);
		e.subscribe(e5.getMess(), s);
		e.subscribe(e6.getMess(), p);
		e.publish(e2);
        //System.out.println(cH);
	}

}
