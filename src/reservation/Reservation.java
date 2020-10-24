package reservation;
import gestionvol.Vol;
import reservation.numberresa.*;

import java.time.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private ZonedDateTime 	mt_dateRes;

    private NR2             mt_numberResa;

    private Vol             mt_volRes; // {frozen}

    private Passager        mt_passager;

/************************************* Constructor *************************************/
    public Reservation(NR2Factory nrf, String name, Vol vol) //passager == client
    {
        this.setNumberRes(nrf);
        this.setPassenger(name);
        this.setDateRes();
        this.setFlight(vol);
        this.mt_volRes.displayFlightWithoutEsc();
        System.out.println("Reservation number : " + this.mt_numberResa.toString());
        System.out.println("New Reservation added");
    }

    public Reservation(NR2Factory nrf, Vol vol)//passager != client
    {
        this.setNumberRes(nrf);
        this.setPassenger();
        this.setDateRes();
        this.setFlight(vol);
        this.mt_volRes.displayFlightWithoutEsc();
        System.out.println("Reservation number : " + this.mt_numberResa.toString());
        System.out.println("New Reservation added");
    }
    
/************************************* getter/setter *************************************/

    public void setNumberRes(NR2Factory nrf) 
    {
        this.mt_numberResa = nrf.getNumbResa();
    }

    public NR2 getNumberRes() 
    {
		return this.mt_numberResa;
	}

	public Vol getFlightRes() {
		return this.mt_volRes;
	}

    public void setFlight(Vol volR)
    {
        this.mt_volRes =  volR;
    }

    public void setPassenger()
    {
        this.mt_passager = new Passager();
    }

    public void setPassenger(String name)
    {
        this.mt_passager = new Passager(name);
    }

    public ZonedDateTime getDateRes()
    {
		return this.mt_dateRes;
	}

    public void setDateRes()
    //not mine : https://mkyong.com/java8/java-8-zoneddatetime-examples/
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm");

        LocalDateTime ldt = LocalDateTime.now();

        this.mt_dateRes = ldt.atZone(ZoneId.of("Europe/Paris")); //by default the reservation is made in France
        System.out.println("Date reservation : " + format.format(this.mt_dateRes));

	}
    
/************************************* methods *************************************/
    public void confirme()
    {

    }

    public void cancel()
    {

    }
}
