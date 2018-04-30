import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;


public class Net {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int t=0;
		while(t<15){
		try{t++;
        Socket s = new Socket("localhost",56123);
		BufferedWriter q=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		System.out.println(s.isConnected()+" "+s.getLocalPort()+" "+s.getPort());
		System.out.print("Line: ");
		String line=in.readLine();
		System.out.println(line);
		q.flush();
		line="dafa";
		char c='a';
		q.write(1);
		q.write(c);
		line="dafa";
		q.write(1);
		c='b';
		q.write(c);
		if(line.equals("~exit"))
			break;
		q.close();
		s.close();}
		catch(Exception e){System.out.println(e);System.out.println(t);}}
	}

}
