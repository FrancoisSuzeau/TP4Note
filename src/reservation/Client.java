package reservation;
import reservation.numberresa.*;
import gestionvol.Vol;

import java.io.*;
import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    private String                      mt_name;
    private NR2Factory 	                mt_numberFactoryResa;
    private Integer                     mt_paiment;
    private Collection<Reservation>     mt_reservation = new ArrayList<>();
    private String                      mt_contact;
	
/************************************* Constructor *************************************/

    public Client(String name)
    {
        this.setName(name);
        this.setContact();
    }

/************************************* getter/setter *************************************/

    public String getName()
    {
        return this.mt_name;
    }

    public void setName(String name)
    {
        this.mt_name = name;
        this.mt_numberFactoryResa = new NR2Factory(getPrefixCli());
        System.out.println("New client created : " + this.mt_name);
    }

    public Integer getPaiment()
    {
        return this.mt_paiment;
    }

    public void setPaiment()
    {
        
    }

    public String getConctat()
    {
        return this.mt_contact;
    }

    //save the phone number
    public void setContact()
    {
        String your_phoneNum = "";
        System.out.println(System.getProperty("line.separator") + ">>>>> Choose a phone number : XX-XX-XX-XX-XX");
        while(true)
        {
            try {

                Reader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
        
                your_phoneNum = br.readLine();
                
            }
            catch(IOException e)
            {
                    
            }

            if(this.validPhoneNumber(your_phoneNum))
            {
                break;
            }
        }

        this.mt_contact = your_phoneNum;
    }

    public Collection<Reservation> getReservation()
    {
		return this.mt_reservation;
	}

    public void setReservation(Vol vol)
    {
        String choice = "";
        int nb_Reser;
        System.out.println(System.getProperty("line.separator") + ">>>>> How many reservation do you want ?");
        while(true)
        {
            try {

                Reader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
        
                choice = br.readLine();
                
            }
            catch(IOException e)
            {
                    
            }
            try {
                nb_Reser = Integer.parseInt(choice);
                //in the uml it said a cardinal of 0 ...* but if the client make a reservation it has to be at least one
                //
                if((nb_Reser < 1) || (nb_Reser > 10)) 
                {
                    System.out.println(System.getProperty("line.separator") + ">>>>> You haven't choose a valide number of reservation [1:10]");
                }
                else
                {
                    break;
                }
                
            } catch (NumberFormatException e) {
                System.out.println("ParseInt failed to convert String in Integer in SetReservation() : May be an empty String");
                System.out.println(">>>> Try again");
            }
        }
        
        int count = 0;
        while(count < nb_Reser)
        {
            //we don't take the case when there will be one passenger and the client is another personne than the passenger
            //like the client is making a gift to somebody
            if(count == 0) 
            {
                this.addReservation(new Reservation(this.mt_numberFactoryResa, this.mt_name, vol)); //the passenger is the client
            }
            else
            {
                this.addReservation(new Reservation(this.mt_numberFactoryResa, vol));
            }
            count++;
        }
	}

/************************************* methods *************************************/

    public String getPrefixCli()
	{
        int end = 0;
        String prefix = "";
		for(int i = 0; i < this.mt_name.length() - 1; i++)
		{
			if(this.mt_name.charAt(i) == ' ')
			{
				end = i;
				break;
			}
        }
        
        prefix = this.mt_name.substring(0, end);
        return prefix;
    }

    public void addReservation(Reservation res)
    {
        try {
            if(!this.mt_reservation.contains(res))
            {
                this.mt_reservation.add(res);
            }
            else
            {
                System.out.println("The reservation you want to add is already in Collection");
            }
            
        } catch (NullPointerException e) {

            System.out.println("The reservation pointer is null in addReservation()");
        }
    
    }

    public void removeReservation(Reservation res)
    {
        try {
            if(this.mt_reservation.contains(res)) // not null and in collection
            {
                this.mt_reservation.remove(res);
            }
            else //not null but does not exist in collection
            {
                System.out.println("The reservation you want to remove on Collection does not exist");
            }
            
        } catch (NullPointerException e) {

            System.out.println("The reservation pointer is null in removeReservation()");
        }
    }

    public boolean validPhoneNumber(String phoneNum)
    //not mine : https://www.codeflow.site/fr/article/java__how-do-validate-phone-number-in-java-regular-expression
    {
        if(!phoneNum.equals(""))
        {
            Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(phoneNum);
    
            if(matcher.matches())
            {
                System.out.println("Your phone number is valide");
                return true;
            }
            else
            {
                System.out.println("Your phone number is invalide");
            }
        }
        else
        {
            System.out.println("Your phone number is invalide");
        }
        return false;
    }
}