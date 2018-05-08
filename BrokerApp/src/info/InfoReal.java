package info;
import java.util.ArrayList;
import java.util.List;

public class InfoReal implements Info {
private List<City> lc;
private List<Road> lr;
	public InfoReal() {
		lc=new ArrayList<City>();
		lr=new ArrayList<Road>();
		lc.add(new City("Timisoara",35));
		lr.add(new Road(45,"deschis"));
	}

	public String getroad_info(int id) {
		for(Road r:lr)
			if(r.id==id)
				return r.info;
		return null;
	}

	public int get_temp(String oras) {
		for(City c:lc)
			   if(c.name.equals(oras))
				      return c.temp;
		return -100;
	}

	

}
