package gestionvol;

import java.time.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.Duration;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.*;
import java.util.*;

import java.lang.*;


import gestionvol.numberflight.*;

public class Vol {

	private NV2 					mt_numberFlight; //The ID of the flight

	private ZonedDateTime 			mt_departure;
	private ZonedDateTime 			mt_arrival;
	private Duration 				mt_duration;

	private Aeroport 				mt_aeroDeparture;
	private Aeroport 				mt_aeroArrival;

	private Collection <Escale> 	mt_escales = new ArrayList<>();

	protected static final LinkedHashMap<String, String> ZONECITY = new LinkedHashMap<>();
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

	public Vol(NV2Factory nvf, String nameD, String nameA)
	{
		this.mt_numberFlight = nvf.getNumbFlight();
		this.initialise_aeroport(nameD, nameA);
		this.setEscale();
		if(this.mt_escales.size() != 0)
		{
			this.initialise_date();
		}
	}

	public Vol()
	{}

/************************************* getter/setter *************************************/

	public String getNumberFlight()
	{
		return this.mt_numberFlight.toString();
	}

	public Collection <Escale> getEscale() 
	{
		return this.mt_escales;
	}

//the aeroport setter
	public void initialise_aeroport(String nameVd, String nameVa)
	{
	
		
		this.mt_aeroArrival = new Aeroport(nameVa);


		this.mt_aeroDeparture = new Aeroport(nameVd);

	}

//the date setter
	public void initialise_date()
	{
		/*we now set the curent time zone (departure or arrival)
		to do so we use the key sent by report_nameCityAirport to find the linked value. This value will get the ID of the zone on the static
		map of ZonedDateTime class*/
		
		ZonedDateTime currentTimeDeparture = ZonedDateTime.now(ZoneId.of(ZONECITY.get(this.mt_aeroDeparture.report_nameCityAirport()))); 
		ZonedDateTime currentTimeArrival = ZonedDateTime.now(ZoneId.of(ZONECITY.get(this.mt_aeroArrival.report_nameCityAirport())));

		this.mt_departure = currentTimeDeparture.plusHours(1).plusMinutes(45);
		this.mt_arrival = currentTimeArrival.plusDays(this.mt_escales.size()); //on va arbitrairement dire que chaque escale prend un jour
	}

//escale collection setter
	public void setEscale()
	{
		ArrayList <String> esc_dispo = new ArrayList<>();

		Set<Entry<String, String>> set_hash = ZONECITY.entrySet();
		Iterator<Entry<String, String>> it = set_hash.iterator();
		Entry <String, String> couple_map;

		while(it.hasNext()) //display all destination available
		{
			couple_map = it.next();
			if((!couple_map.getKey().equals(this.mt_aeroDeparture.report_nameCityAirport())) && 
			(!couple_map.getKey().equals(this.mt_aeroArrival.report_nameCityAirport())))
			{
				esc_dispo.add(couple_map.getKey()); //a list of city without the city departure and arrival of the flight
			}

		}

		Random rand = new Random();
		int nb_esc = rand.nextInt(esc_dispo.size() + 1); //between 0 and total city availaible

		if(nb_esc == 0)
		{
			return; //this flight does not contain escale
		}
		else
		{
			int count = 0;
			while (count < nb_esc)
			{
				int cityD = rand.nextInt(esc_dispo.size());
				int cityA = rand.nextInt(esc_dispo.size());
				if((cityD >= esc_dispo.size() - 1) && (cityA >= esc_dispo.size() - 1))
				{
					this.addEscale(new Escale(esc_dispo.get(esc_dispo.size() - 1), esc_dispo.get(esc_dispo.size() - 2)));
					esc_dispo.remove(esc_dispo.size()-1);
					esc_dispo.remove(esc_dispo.size()-1);
				}
				else if((cityA >= esc_dispo.size() - 1) && (cityD < esc_dispo.size() - 1))
				{
					this.addEscale(new Escale(esc_dispo.get(cityD), esc_dispo.get(esc_dispo.size() - 1)));
					esc_dispo.remove(esc_dispo.size()-1);
					esc_dispo.remove(cityD);

				}
				else if((cityD >= esc_dispo.size() - 1) && (cityA < esc_dispo.size()))
				{
					this.addEscale(new Escale(esc_dispo.get(esc_dispo.size() - 1), esc_dispo.get(cityA)));
					esc_dispo.remove(esc_dispo.size()-1);
					esc_dispo.remove(cityA);
				}
				else
				{
					this.addEscale(new Escale(esc_dispo.get(cityD), esc_dispo.get(cityA)));
					esc_dispo.remove(cityD);
					esc_dispo.remove(cityA);
				}

				if(esc_dispo.size() == 0)
				{
					break;
				}
				else
				{
					count++;
				}
			}

			if(this.mt_escales != null)
			{
				for(Escale e : this.mt_escales)
				{
					e.setFlightWithoutBi(this);
					if(e.getFlight() == null)
					{
						throw new IllegalArgumentException("Double navigability failed in setEscale");
					}
				}
			}
			
		}
	}

	

/************************************* methods *************************************/

	

	//this methods add a new escale
	public void addEscale(Escale esc)
	{
		esc.setFlightWithoutBi(this);
		/*if(esc.getFlight() == null)
		{
			throw new IllegalArgumentException("Double navigability failed in addEscale");
		}*/
		try {
			this.mt_escales.add(esc);
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No escale available in addEscale");
		}
		

	}

	//this methods remove an escale
	public void removeEscale(Escale esc)
	{
		esc.setFlightWithoutBi(null);
		this.mt_escales.remove(esc);
	}

	@Override
	public String toString()
	{
		return this.mt_numberFlight.toString();
	}

/************************************* without bidirectionnal *************************************/

	protected void addEscaleWithoutBi(Escale esc)
	{
		try {
			this.mt_escales.add(esc);
			
		} catch (Exception e) {
			throw new IllegalArgumentException("No escale available in addEscale");
		}
		

	}

	protected void removeEscaleWithoutBi(Escale esc)
	{
		this.mt_escales.remove(esc);
	}


	protected void setEscaleWithoutBi()
	{
		ArrayList <String> esc_dispo = new ArrayList<>();

		Set<Entry<String, String>> set_hash = ZONECITY.entrySet();
		Iterator<Entry<String, String>> it = set_hash.iterator();
		Entry <String, String> couple_map;

		while(it.hasNext()) //display all destination available
		{
			couple_map = it.next();
			if((!couple_map.getKey().equals(this.mt_aeroDeparture.report_nameCityAirport())) && 
			(!couple_map.getKey().equals(this.mt_aeroArrival.report_nameCityAirport())))
			{
				esc_dispo.add(couple_map.getKey()); //a list of city without the city departure and arrival of the flight
			}

		}

		Random rand = new Random();
		int nb_esc = rand.nextInt(esc_dispo.size() + 1); //between 0 and total city availaible

		if(nb_esc == 0)
		{
			return; //this flight does not contain escale
		}
		else
		{
			int count = 0;
			while (count < nb_esc)
			{
				int cityD = rand.nextInt(esc_dispo.size());
				int cityA = rand.nextInt(esc_dispo.size());
				if((cityD >= esc_dispo.size() - 1) && (cityA >= esc_dispo.size() - 1))
				{
					this.addEscale(new Escale(esc_dispo.get(esc_dispo.size() - 1), esc_dispo.get(esc_dispo.size() - 2)));
					esc_dispo.remove(esc_dispo.size()-1);
					esc_dispo.remove(esc_dispo.size()-1);
				}
				else if((cityA >= esc_dispo.size() - 1) && (cityD < esc_dispo.size() - 1))
				{
					this.addEscale(new Escale(esc_dispo.get(cityD), esc_dispo.get(esc_dispo.size() - 1)));
					esc_dispo.remove(esc_dispo.size()-1);
					esc_dispo.remove(cityD);

				}
				else if((cityD >= esc_dispo.size() - 1) && (cityA < esc_dispo.size()))
				{
					this.addEscale(new Escale(esc_dispo.get(esc_dispo.size() - 1), esc_dispo.get(cityA)));
					esc_dispo.remove(esc_dispo.size()-1);
					esc_dispo.remove(cityA);
				}
				else
				{
					this.addEscale(new Escale(esc_dispo.get(cityD), esc_dispo.get(cityA)));
					esc_dispo.remove(cityD);
					esc_dispo.remove(cityA);
				}

				if(esc_dispo.size() == 0)
				{
					break;
				}
				else
				{
					count++;
				}
			}			
		}
	}

	public void displayFlight()
	{
		System.out.println("Number flight : " + this.mt_numberFlight.toString());
		System.out.println(System.getProperty("line.separator") + "------ Details ------");
		System.out.println("Aeroport departure : " + this.mt_aeroDeparture.getNameAirport());
		System.out.println(">>> Date departure 	: " + this.mt_departure);
		System.out.println("Aeroport arrival : " + this.mt_aeroArrival.getNameAirport());
		System.out.println(">>> Date arrival 	: " + this.mt_arrival);
		if(this.mt_escales.size() != 0)
		{
			
			for(Escale e : this.mt_escales)
			{
				e.displayEscale();
			}
		}
	}

	public void displayFlightWithoutEsc()
	{
		System.out.println("Number flight : " + this.mt_numberFlight.toString());
		System.out.println(System.getProperty("line.separator") + "------ Details ------");
		System.out.println("Aeroport departure : " + this.mt_aeroDeparture.getNameAirport());
		System.out.println(">>> Date departure 	: " + this.mt_departure);
		System.out.println("Aeroport arrival : " + this.mt_aeroArrival.getNameAirport());
		System.out.println(">>> Date arrival 	: " + this.mt_arrival);
	}
}