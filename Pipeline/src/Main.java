public class Main {

	public static void main(String[] args) {
      Pipe p1=new Pipe(),p2=new Pipe();
      Chair c1=new Chair(),c2=new Chair();
      Filter fb=new FilterB();
      Filter fc=new FilterC();
      Filter ff=new FilterF();
      Filter fp=new FilterP();
      Filter fs=new FilterS();
      p1.addFilter(fc);
      p1.addFilter(fb);
      p1.addFilter(ff);
      p1.addFilter(fs);
      p1.addFilter(fp);
      p2.addFilter(fc);
      p2.addFilter(ff);
      p2.addFilter(fs);
      p2.addFilter(fp);
      p1.process(c1);
      p2.process(c2);
      System.out.println(c1);
      System.out.println(c2);
      
	}

}
