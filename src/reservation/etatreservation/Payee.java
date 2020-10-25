package reservation.etatreservation;

public class Payee extends EtatReservation {

    private EtatReservation mt_etat;
    
    public Payee()
    {
        super();
    }

    public void confirmer()
    {

    }

    public void annuler()
    {
        this.mt_etat.rembourser();
    }


}
