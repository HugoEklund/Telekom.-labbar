import java.util.*;

public class DispatcherRand extends Gen 
{
    private double lambda;
    private ArrayList<QS> someSystems;

	public DispatcherRand(ArrayList<QS> qs, double lambda)
	{
        this.someSystems = qs;
        this.lambda = lambda;
	}
    
    public void TreatSignal(Signal x) 
    {
        switch (x.signalType) 
        {
            case READY:
            {
                Random slump = new Random();
                int rngSys = slump.nextInt(5);
                SignalList.SendSignal(ARRIVAL, someSystems.get(rngSys), time);
                SignalList.SendSignal(READY, this, time + (2.0 / lambda) * slump.nextDouble());
            }
            break;
        }
    }
}