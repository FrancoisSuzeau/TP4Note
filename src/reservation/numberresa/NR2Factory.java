package numberresa;

public class NR2Factory extends NRFactory {

    public NR2Factory(String prefix)
	{
		super(prefix);
	}

	@Override
	synchronized public NR2 getNumbResa()
	{
		return new NR2(String.format("%S - %06d", this.mt_prefix, this.mt_count++));
	}
    
}
