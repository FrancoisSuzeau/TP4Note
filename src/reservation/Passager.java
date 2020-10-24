package reservation;

import java.io.*;
import java.util.*;

public class Passager {
    
    private String mt_name;

/************************************* Constructor *************************************/
    public Passager(String name)
    {
       setName(name);
    }

    public Passager()
    {
        setName();
    }

/************************************* getter/setter *************************************/

    public String getName()
    {
		return this.mt_name;
	}

    public void setName(String name)
    {
        this.mt_name = name;
        System.out.println("New Passenger added in data base : " + this.getName());
    }
    
    public void setName()
    {
        String nme = "";
        System.out.println(System.getProperty("line.separator") + ">>>>> Choose a passenger name");
		while(true)
		{
			try {
	
					
				Reader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
		
				nme = br.readLine();
				if((nme.equals("")) || (nme.length() == 1))
        		{
            		System.out.println("You haven't choose a passenger name");
				}
				else
				{
					break;
				}
			}
			catch(IOException e)
			{
					
			}
        }
        
        this.mt_name = nme;
        System.out.println("New Passenger added in data base : " + this.getName());
    }
}
