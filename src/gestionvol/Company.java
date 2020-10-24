package gestionvol;
import gestionvol.numberflight.*;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.*;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Company {

	private Collection <Vol> 		mt_flight = new ArrayList<>();
	//private Vol 					mt_flight;
	private NV2Factory 				mt_numberFactory;	//the factory who made the number of the flight
	private String 					mt_name;			//name of the company
	private String 					mt_prefix; 			//prefix used for the number of the flight


/************************************* Constructor *************************************/
	public Company(String name)
	{
		if((name.equals("")) || (name.length() == 1))
		{
			throw new IllegalArgumentException("No name available in Company constructor's");
		}
		else
		{
			this.mt_name = name;
			getPrefix();
			this.mt_numberFactory = new NV2Factory(this.mt_prefix);
			this.displayCity();
			System.out.println(System.getProperty("line.separator") + ">>>>> Where do You want to go ? :");
			String cityA = search_city();
			System.out.println(System.getProperty("line.separator") + ">>>>> From where ? :");
			String cityD = search_city();
			setFlight(cityD, cityA);
		}
		
	}

/************************************* getter/setter *************************************/
	public Vol getFlight(String idFlight)
	{
		Vol volAddRes = new Vol();
		if(this.isIDflightValide(idFlight))
		{
			for(Vol v : this.mt_flight)
			{
				if(v.getNumberFlight().equals(idFlight))
				{
					volAddRes = v;
				}
			}
		}

		return volAddRes;
	}

/************************************* methods *************************************/

	public boolean isIDflightValide(String idFLight)
	{
		if(!idFLight.equals(""))
        {
            Pattern pattern = Pattern.compile("\\S+ - \\d{6}");
            Matcher matcher = pattern.matcher(idFLight);
    
            if(matcher.matches())
            {
                System.out.println("Your flight id is valide");
                return true;
            }
            else
            {
                System.out.println("Your flight id is invalide");
            }
        }
        else
        {
            System.out.println("Your flight id is invalide");
        }
        return false;
	}

	public void getPrefix() //make the prefix used for all number of flights
	{
		int end;
		
		if(this.mt_name.length() < 6)
		{
			end = this.mt_name.length();
		}
		else if(this.mt_name.length() >= 11)
		{
			end = 5;
		}
		else
		{
			end = this.mt_name.length()/3;
		}

		this.mt_prefix = this.mt_name.substring(0, end);
	
		
	}

	public void setFlight(String nameD, String nameA) //construct the flight
	{
		Random rand = new Random();
		int nb_vol = rand.nextInt(5);
		System.out.println("There is " + nb_vol + " flight available");
		if(nb_vol != 0)
		{
			int count = 0;
			while(count < nb_vol)
			{
				this.addVol(new Vol(this.mt_numberFactory, nameD, nameA));
				count++;
			}
			
		}
		
	}

	public void displayCity()
	{
		System.out.println(System.getProperty("line.separator") + " ------ Destination and departure available ------ ");

		Set<Entry<String, String>> set_hash = Vol.ZONECITY.entrySet();
		Iterator<Entry<String, String>> it = set_hash.iterator();
		Entry <String, String> couple_map;

		while(it.hasNext()) //display all destination available
		{
			couple_map = it.next();
			System.out.println("-- " + couple_map.getKey());
		}
	}

	public void display()
	{
		for(Vol v : this.mt_flight)
		{
			v.displayFlight();
			System.out.println(System.getProperty("line.separator"));
		}
	}

	public void addVol(Vol vol)
	{
		this.mt_flight.add(vol);
	}

	public void removeVol(Vol vol)
	{
		this.mt_flight.add(vol);
	}

	public int getnb_flight()
	{
		return this.mt_flight.size();
	}

	//this method ask to the user to choose the city of departure or arrival (depends wich aeroport call it)
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

			if(Vol.ZONECITY.containsKey(choice))
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