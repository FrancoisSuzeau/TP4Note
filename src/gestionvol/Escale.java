package gestionvol;

import java.time.temporal.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;




public class Escale {

    private Aeroport        mt_aeroportDestination;
    private ZonedDateTime 	mt_EscDepartureTime;
    private ZonedDateTime 	mt_EscArrivalTime;
    private Ville           mt_EscCity;

	

    private Vol             mt_flight; //for double navigability

/************************************* Constructor *************************************/

    public Escale(String nameVd, String nameVesc)
    {
        this.mt_aeroportDestination = new Aeroport(nameVd);
        setVille(nameVesc);
        setDate();
    }

/************************************* getter/setter *************************************/

    public Vol getFlight()
    {
        return this.mt_flight;
    }

    public void setFlight(Vol vol)
    {
        if(vol != null) //double navigability
        {
            vol.addEscaleWithoutBi(this);
        }

        if(this.mt_flight != null) //if we change the company of the flight
        {
            vol.removeEscaleWithoutBi(this);
        }
       
        this.mt_flight = vol;
    }

    protected void setFlightWithoutBi(Vol vol)
    {
        this.mt_flight = vol;
    }

    public String getVille()
    {
		return this.mt_EscCity.toString();
	}

    public void setVille(String name)
    {
		this.mt_EscCity = new Ville(name);
	}

    public void setDate()
    {  
        /*we now set the curent time zone (departure or arrival)
        to do so we use the key sent by report_nameCityAirport and toString to find the linked value. This value will get the ID of the zone on the static
        map of ZonedDateTime class*/

        //ZonedDateTime currentDeparture = ZonedDateTime.now(ZoneId.of(Vol.ZONECITY.get(mt_EscCity.toString())));

        
        this.mt_EscArrivalTime = ZonedDateTime.now(ZoneId.of(Vol.ZONECITY.get(this.mt_aeroportDestination.report_nameCityAirport())));
        this.mt_EscDepartureTime = this.mt_EscArrivalTime.plusHours(1).plusMinutes(45);
    }

    public Duration getDuration()
    {
        if((this.mt_EscDepartureTime != null) && (this.mt_EscArrivalTime != null) )
        {
            return Duration.of(this.mt_EscDepartureTime.until(this.mt_EscArrivalTime, ChronoUnit.MILLIS), ChronoUnit.MILLIS );
        }
        return null;
    }


    public ZonedDateTime getDateDeparture()
    {
        return this.mt_EscDepartureTime;
    }

    public ZonedDateTime getDateArrival()
    {
        return this.mt_EscDepartureTime;
    }

    

/************************************* methods *************************************/

    @Override
    public boolean equals(Object obj)
    {
        try {
            return ((Escale)obj).getVille().equals(this.mt_EscCity.toString());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString()
    {
        return this.mt_flight.toString();
    }

    public void displayEscale()
    {
        System.out.println(System.getProperty("line.separator") + "------ Details Escales ------");
		System.out.println(this.getVille());
		System.out.println("Date arrival escale 	: " + this.getDateArrival());
		System.out.println("Date departure escale 	: " + this.getDateDeparture());
    }
}
