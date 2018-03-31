import java.util.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new String("Ana are mere").matches("(.*)"+"are"+"(.*)"));
     BlackBoard b=new BlackBoard();
     KS ksb=new KSB(b);
     KS ksc=new KSC(b);
     KS ksf=new KSF(b);
     KS kspnb=new KSPnb(b);
     KS kspfull=new KSPfull(b);
     KS kss=new KSS(b);
     Control c =new Control(b);
     List<KS> ks=new ArrayList<KS>(),ks1=new ArrayList<KS>();
     //ks.add(ksb);
     ks.add(ksc);
     ks.add(ksf);
     ks.add(kspnb);
     ks.add(kss);
     Chair ch=new Chair(ks);
     ks1.add(ksc);
     ks1.add(ksf);
     ks1.add(kspfull);
     ks1.add(kss);
     ks1.add(ksb);
     Chair ch1=new Chair(ks1);
     b.addChair(ch);
     b.addChair(ch1);
     c.loop();
     System.out.println(b);
	}

}
