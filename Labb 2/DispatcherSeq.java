import java.util.*;

public class DispatcherSeq extends Gen
{
    private double lambda;
    private ArrayList<QS> someSystems;
    private int sysIndex;

	public DispatcherSeq(ArrayList<QS> qs, double lambda)
	{
        this.someSystems = qs;
        this.lambda = lambda;
        sysIndex = 0;
	}
    
    public void TreatSignal(Signal x)
    {
        switch (x.signalType) 
        {
            case READY:
            {
                sysIndex = (sysIndex + 1) % someSystems.size();

                SignalList.SendSignal(ARRIVAL, someSystems.get(sysIndex), time);
                SignalList.SendSignal(READY, this, time + (2.0 / lambda) * slump.nextDouble());
            }
            break;
        }
    }
}
