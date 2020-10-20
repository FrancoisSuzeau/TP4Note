package numberflight;

public class NV2Factory extends NVFactory {

	public NV2Factory(String prefix)
	{
		super(prefix);
	}

	@Override
	synchronized public NV2 getNumbFlight()
	{
		return new NV2(String.format("%S - %06d", this.mt_prefix, this.mt_count++));
	}
}