package numberflight;

public class NVFactory {

	protected int 			mt_count = 0;
	protected final String 	mt_prefix;


	public NVFactory(String prefix)
	{
		this.mt_prefix = prefix;
	}

	synchronized public NumberFlight getNumbFlight()
	{
		return new NumberFlight(String.format("%S - %06d", this.mt_prefix, this.mt_count++));
	}
}