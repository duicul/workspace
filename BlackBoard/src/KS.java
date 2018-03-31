import java.util.*;


public abstract class KS {
 protected String s;
 protected BlackBoard b;
 protected List<String> check;
 public KS(String s,BlackBoard b)
 {this.check=new ArrayList<String>();
 this.s=s;
 this.b=b;}
 
 public boolean execCondition()
 {return b.inspect().check(check,this.toString());}
 
 public void execAction()
 {b.inspect().addString(s);}
 
 public void updateBB()
 {if(b.inspect().complete())
  b.update();
 else
	 b.inspect().addString(s);
 }
 
 public String toString()
 {return s;}
 
}
