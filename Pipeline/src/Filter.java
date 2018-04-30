
public abstract class Filter {
 protected String s;
 public Filter(String s)
 {this.s=s;}
 public void process(Chair c) {
		c.addString(s);}
}
