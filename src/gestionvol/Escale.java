package gestionvol;
import java.time.*;

public class Escale {

    private Aeroport        mt_aeroportArrival;
    private ZonedDateTime 	mt_EscDeparture;
    private ZonedDateTime 	mt_EscArrival;

    public Escale(String nameA, String nameV)
    {
        this.mt_aeroportArrival = new Aeroport(nameA, nameV);
        


    }
    
}
