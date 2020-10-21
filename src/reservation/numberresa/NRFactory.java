package reservation.numberresa;

public class NRFactory {

    protected int 			mt_count = 0;
	protected final String 	mt_prefix;


	public NRFactory(String prefix)
	{
		this.mt_prefix = prefix;
	}

	synchronized public NumberResa getNumbResa()
	{
		return new NumberResa(String.format("%S - %06d", this.mt_prefix, this.mt_count++));
	}
    
}
