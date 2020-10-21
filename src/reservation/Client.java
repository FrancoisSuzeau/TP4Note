package reservation;
import reservation.numberresa.*;

public class Client {

    private String mt_name;
    private NR2Factory 	mt_numberFactoryResa;


    public Client(String name)
    {
        if((name.equals("")) || (name.length() == 1))
        {
            throw new IllegalArgumentException("No name available in client constructor");
        }
        else
        {
            this.mt_name = name;
            this.mt_numberFactoryResa = new NR2Factory(getPrefixCli());
        }

        System.out.println("New client created : " + this.mt_name);
    }

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
}
