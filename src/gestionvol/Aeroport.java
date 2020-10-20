package gestionvol;


public class Aeroport {

    private String  mt_name;
    private Ville   mt_city;


    //also here we want one constructor with parameter available because an aeroport with no name cannot exist
    //city will manage his own construct parametor
    public Aeroport(String nameA, String nameV) throws IllegalArgumentException
    {
        if((nameA.equals("")) || (nameA.length() == 1))
        {
            throw new IllegalArgumentException("No name available for aeroport constructor");
        }

        this.mt_name = nameA;
        this.mt_city = new Ville(nameV);
    }
    
}
