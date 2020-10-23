package gestionvol;

public class Ville {

    private String  mt_name; //the attribute name of the city

    
/************************************* Constructor *************************************/
    //we need only one constructor because a city has to have a name
    public Ville(String nom) throws IllegalArgumentException
    {
        if((nom.equals("")) || (nom.length() == 1))
        {
            throw new IllegalArgumentException("No name available in Ville constructor");
        }

        this.mt_name = nom;
    }

/************************************* methods *************************************/

    @Override
    public String toString()
    {
        return this.mt_name;
    }
}