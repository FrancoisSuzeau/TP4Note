import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.Map.Entry;
import java.io.*;
import java.util.*;

import gestionvol.Company;
import reservation.Client;

public class Main {

	private static final LinkedHashMap<Integer, String> COMPANYNAME = new LinkedHashMap<>();
	static {
		COMPANYNAME.put(1, "Air France");
		COMPANYNAME.put(2, "Air Algerie");
		COMPANYNAME.put(3, "Czech Airlines Csa");
		COMPANYNAME.put(4, "Bangkok Airway");
		COMPANYNAME.put(5, "Japan Airlines");
		COMPANYNAME.put(6, "Middle East Airlines");
		COMPANYNAME.put(7, "Onu Air");
		COMPANYNAME.put(8, "Swiss International Air Lines");
		COMPANYNAME.put(9, "Pegasus Airlines");
		COMPANYNAME.put(10, "Virgin Atlantic");
		COMPANYNAME.put(11, "Israir Airlines");
		COMPANYNAME.put(12, "El Israel Airlines");
		COMPANYNAME.put(13, "Brussels Airlines");
		COMPANYNAME.put(14, "Air Cote Ivoir");
		COMPANYNAME.put(15, "Austrian Airlines");
	  }


	public static void main(String[] args) {

		Set<Entry<Integer, String>> set_hash = COMPANYNAME.entrySet();
		Iterator<Entry<Integer, String>> it = set_hash.iterator();
		Entry <Integer, String> couple_hashMap;

		System.out.println(">>> Company List :");

		while(it.hasNext())
		{
			couple_hashMap = it.next();
			System.out.println(couple_hashMap.getKey() + " : " + couple_hashMap.getValue());
		}

		String choice = "";
		int keychoice = 0;
		System.out.println(System.getProperty("line.separator") + ">>>>> Choose a Company");

		while ((keychoice < 1) || (keychoice > 16))
		{
			try {

				
				Reader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
		
				choice = br.readLine();
				
			}
			catch(IOException e)
			{
					
			}

			keychoice = Integer.parseInt(choice);
			if((keychoice < 1) || (keychoice > 16))
			{
				System.out.println(System.getProperty("line.separator") + ">>>>> You haven't choose a company, please choose between [1:15]");

			}
		}

		System.out.println(System.getProperty("line.separator") + ">>>>> You choose the company : " + COMPANYNAME.get(keychoice));
	}
}