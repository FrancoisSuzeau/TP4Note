package gestionvol;
import java.time.*;
import numberflight.*;

public class Vol {

	private NV2 			mt_numberFlight;

	private ZonedDateTime 	mt_departure;
	private ZonedDateTime 	mt_arrival;
	private Duration 		mt_duration;

	private Aeroport 		mt_aeroDeparture;
	private Aeroport 		mt_aeroArrival;

	public Vol(NV2Factory nvf)
	{
		this.mt_numberFlight = nvf.getNumbFlight();
	}
}