package reservation;
import java.time.*;
import gestionvol.Vol;
import reservation.numberresa.*;

public class Reservation {

    private ZonedDateTime 	mt_dateRes;

    private NR2             mt_numberResa;

    private Vol             mt_volRes;

/************************************* Constructor *************************************/
    public Reservation(NR2Factory nrf)
    {
        this.setNumberRes(nrf);
    }
    
/************************************* getter/setter *************************************/
    public void setdateRes(ZonedDateTime dateRes) 
    {
        this.mt_dateRes = mt_dateRes;
    }

    public ZonedDateTime getdateRes()
    {
		return this.mt_dateRes;
    }
    
    public void setNumberRes(NR2Factory nrf ) 
    {
		this.mt_numberResa = nrf.getNumbResa();
	}

    public NR2 getNumberRes() 
    {
		return this.mt_numberResa;
	}

	public Vol getFlight() {
		return this.mt_volRes;
	}

	public void setFlight(Vol volR) {
		this.mt_volRes =  volR;
    }
    
/************************************* methods *************************************/
    public void confirme()
    {

    }

    public void cancel()
    {

    }
}
