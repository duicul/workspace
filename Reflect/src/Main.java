
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Loop l=new Loop("/home/duicul/Desktop/");
		while(true)
		try{
		l.run();
		//l.showClasses();
		Thread.sleep(5000);
		}
		catch(Exception e)
		{e.printStackTrace();}
	}

}
