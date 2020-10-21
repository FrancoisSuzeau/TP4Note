package reservation;
import java.time.*;
import gestionvol.Vol;
import reservation.numberresa.*;

public class Reservation {

    private ZonedDateTime 	mt_dateRes;
    private NR2      mt_numberResa;

    private Vol             mt_volRes;

    public Reservation(NR2Factory nrf)
    {
        this.mt_numberResa = nrf.getNumbResa();
    }
    
    

    public void confirme()
    {

    }

    public void cancel()
    {

    }
}
