package client;

import compute.Compute;
import java.io.Serializable;

public class Val implements Compute, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int execute(int t) {
        return t*10;
    }

}