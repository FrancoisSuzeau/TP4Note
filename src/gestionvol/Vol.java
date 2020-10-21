package gestionvol;

import java.time.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.*;
import java.util.*;


import gestionvol.numberflight.*;

public class Vol {

	private NV2 			mt_numberFlight; //The ID of the flight

	private ZonedDateTime 	mt_departure;
	private ZonedDateTime 	mt_arrival;
	private Duration 		mt_duration;

	private Aeroport 		mt_aeroDeparture;
	private Aeroport 		mt_aeroArrival;

	private static final LinkedHashMap<String, String> ZONECITY = new LinkedHashMap<>();
	static {
		ZONECITY.put("Darwin", "Australia/Darwin");
		ZONECITY.put("Sydney", "Australia/Sydney");
		ZONECITY.put("Buenos Aires", "America/Argentina/Buenos_Aires");
		ZONECITY.put("Cairo", "Africa/Cairo");
		ZONECITY.put("Anchorage", "America/Anchorage");
		ZONECITY.put("Sao Paulo", "America/Sao_Paulo");
		ZONECITY.put("Dhaka", "Asia/Dhaka");
		ZONECITY.put("Harare", "Africa/Harare");
		ZONECITY.put("St Johns", "America/St_Johns");
		ZONECITY.put("Chicago", "America/Chicago");
		ZONECITY.put("Shanghai", "Asia/Shanghai");
		ZONECITY.put("Addis Ababa", "Asia/Addis_Ababa");
		ZONECITY.put("Paris", "Europe/Paris");
		ZONECITY.put("Indianapolis", "America/Indiana/Indianapolis");
		ZONECITY.put("Kolkata", "Asia/Kolkata");
		ZONECITY.put("Tokyo", "Asia/Tokyo");
		ZONECITY.put("Apia", "Pacific/Apia");
		ZONECITY.put("Yerevan", "Asia/Yerevan");
		ZONECITY.put("Auckland", "Pacific/Auckland");
		ZONECITY.put("Karachi", "Asia/Karachi");
		ZONECITY.put("Phoenix", "America/Phoenix");
		ZONECITY.put("Puerto Rico", "America/Puerto_Rico");
		ZONECITY.put("Los Angeles", "America/Los_Angeles");
		ZONECITY.put("Honiara", "Pacific/Guadalcanal");
		ZONECITY.put("Ho Chi Minh", "Asia/Ho_Chi_Minh");
	  }

/************************************* Constructor *************************************/

	public Vol(NV2Factory nvf)
	{
		this.mt_numberFlight = nvf.getNumbFlight();
		initialise_aeroport();
		initialise_date();
	}

/************************************* methods *************************************/

	public void initialise_aeroport()
	{
		System.out.println(System.getProperty("line.separator") + ">>>>> Where do You want to go ? :");

		Set<Entry<String, String>> set_hash = ZONECITY.entrySet();
		Iterator<Entry<String, String>> it = set_hash.iterator();
		Entry <String, String> couple_map;

		while(it.hasNext())
		{
			couple_map = it.next();
			System.out.println("-- " + couple_map.getKey());
		}
	
		String nameVa = search_city();
		this.mt_aeroArrival = new Aeroport(nameVa);

		System.out.println(System.getProperty("line.separator") + ">>>>> From where ? :");
		String nameVd = search_city();
		this.mt_aeroDeparture = new Aeroport(nameVd);

	}

	public void initialise_date()
	//not mine for most of the parts, here is the link : https://mkyong.com/java8/java-8-zoneddatetime-examples/ section 2
	{
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); //making the format of display and used

		LocalDateTime ldt = LocalDateTime.now(); //get the local time of the default clock system
		System.out.println("LocalDateTime :  " + format.format(ldt));

		this.mt_departure = ldt.atZone(ZoneId.of("Asia/Kuala_Lumpur"/*ZONECITY.get(this.mt_aeroDeparture.report_nameCityAirport())*/)); //get the zoned time said by the parameter of ZoneId.of()
		System.out.println("Departure : " + format.format(mt_departure));

		ZoneOffset offset = ZoneOffset.of("Z"); //the offset use here is : UTC
		this.mt_arrival = this.mt_departure.withZoneSameInstant(offset).plusHours(8).plusMinutes(10);
		System.out.println("Arrival : " + format.format(mt_arrival));

		System.out.println(System.getProperty("line.separator") + "---- Details ----");
		System.out.println("Departure : " + this.mt_departure);
		System.out.println("Arrival : " + this.mt_arrival);
	}

	public String search_city()
	{
		boolean choice_not_ok = true;
		String choice = "";
		while (choice_not_ok)
		{
			try {

				Reader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
		
				choice = br.readLine();
				
			}
			catch(IOException e){}

			if(ZONECITY.containsKey(choice))
			{
				choice_not_ok = false;
			}
			else
			{
				System.out.println(System.getProperty("line.separator") + ">>>>> No city with that name in the Data Base");
			}
		}

		return choice;
	}
}