package reservation;

public class Passager {
    
    private String mt_name;


    public Passager(String name) throws IllegalArgumentException
    {
        if((name.equals("")) || (name.length() == 1))
        {
            throw new IllegalArgumentException("No argument available in passager constructor");
        }

        this.mt_name = name;
    }
}
