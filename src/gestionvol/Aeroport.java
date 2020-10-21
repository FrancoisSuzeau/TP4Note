package gestionvol;

import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.*;
import java.util.*;


public class Aeroport {

    private String  mt_name; //the attribute name of the airport
    private Ville   mt_city; //the city where the airport is

    private static final LinkedHashMap<String, String> AEROPORTNAME = new LinkedHashMap<>(); //the Data base name of the airports associate with their city
	static {
		AEROPORTNAME.put("Darwin", "Darwin International Airport");
		AEROPORTNAME.put("Sydney", "Sir Charles Kingsford Smith Sydney Airport");
		AEROPORTNAME.put("Buenos Aires", "Ezeiza International Airport");
		AEROPORTNAME.put("Cairo", "Cairo International Airport");
		AEROPORTNAME.put("Anchorage", "Ted Stevens Anchorage International Airport");
		AEROPORTNAME.put("Sao Paulo", "Guarulhos International Airport");
		AEROPORTNAME.put("Dhaka", "Shah Jalal International Airport");
		AEROPORTNAME.put("Harare", "Harare International Airport");
		AEROPORTNAME.put("St Johns", "St Johns International Airport");
		AEROPORTNAME.put("Chicago", "O'Hare Chicago International Airport");
		AEROPORTNAME.put("Shanghai", "Shanghai-Pudong International Airport");
		AEROPORTNAME.put("Addis Ababa", "Addis Ababa/Bole International Airport");
		AEROPORTNAME.put("Paris", "Roissy Charle de Gaulle International Airport");
		AEROPORTNAME.put("Indianapolis", "Indianapolis International Airport");
		AEROPORTNAME.put("Kolkata", "Netaji Subhash Chandra Bose International Airport");
		AEROPORTNAME.put("Tokyo", "Haneda Tokyo International Airport");
		AEROPORTNAME.put("Apia", "Faleolo International Airport");
		AEROPORTNAME.put("Yerevan", "Zvartnots International Airport");
		AEROPORTNAME.put("Auckland", "Auckland International Airport");
		AEROPORTNAME.put("Karachi", "Jinnah Karachi International Airport");
		AEROPORTNAME.put("Phoenix", "Sky Harbor Phoenix International Airport");
		AEROPORTNAME.put("Puerto Rico", "Luis Munoz Marin International Airport");
		AEROPORTNAME.put("Los Angeles", "Los Angeles International Airport");
		AEROPORTNAME.put("Honiara", "Honiara International Airport");
		AEROPORTNAME.put("Ho Chi Minh", "Tan Son Nhut International Airport");
	  }

/************************************* Constructor *************************************/

    //also here we want one constructor with parameter available because an aeroport with no name cannot exist
    //city will manage his own construct parametor
    public Aeroport(String nameV) throws IllegalArgumentException
    {
        if((nameV.equals("")) || (nameV.length() == 1))
        {
            throw new IllegalArgumentException("No name available for aeroport constructor");
        }

        this.mt_city = new Ville(nameV);
        System.out.println("You chose : " + this.mt_city.getNameCity());

        this.mt_name = AEROPORTNAME.get(this.mt_city.getNameCity());
        System.out.println("The name of the airports is : " + this.getNameAirport());

    }

/************************************* methods *************************************/

    public String getNameAirport()
    {
        return this.mt_name;
	}
	
	public String report_nameCityAirport()
	{
		Set<Entry<String, String>> set_hash = AEROPORTNAME.entrySet();
		Iterator<Entry<String, String>> it = set_hash.iterator();
		Entry <String, String> couple_hashMap;

		String report = "";
		while(it.hasNext())
		{
			couple_hashMap = it.next();
			if(couple_hashMap.getValue() == this.getNameAirport())
			{
				report = couple_hashMap.getKey();
				break;
			}
		}

		return report;
	}
}
