package gestionvol;
import numberflight.*;

public class Company {

	private Vol 		mt_flight;			//the flight
	private NV2Factory 	mt_numberFactory;	//the factory who made the number of the flight
	private String 		mt_name;			//name of the company
	private String 		mt_prefix; 			//prefix used for the number of the flight


/************************************* Constructor *************************************/
	public Company(String name)
	{
		this.mt_name = name;
		getPrefix();
		this.mt_numberFactory = new NV2Factory(this.mt_prefix);
		getFlight();
	}

/************************************* methods *************************************/

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

	public void getFlight() //construct the flight
	{
		this.mt_flight = new Vol(this.mt_numberFactory);
	} 
}