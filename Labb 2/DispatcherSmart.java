import java.util.*;

public class DispatcherSmart extends Gen
{
    private double lambda;
    private ArrayList<QS> someSystems;

	public DispatcherSmart(ArrayList<QS> qs, double lambda)
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
                SignalList.SendSignal(ARRIVAL, someSystems.get(findOptimalSys()), time);
                SignalList.SendSignal(READY, this, time + (2.0 / lambda) * slump.nextDouble());
            }
            break;
        }
    }

    public int findOptimalSys() 
    {
        int minJobs = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < someSystems.size(); i++)
        {
            if (someSystems.get(i).numberInQueue < minJobs)
            {
                minJobs = someSystems.get(i).numberInQueue;
                minIndex = i;
            }
        }

        return minIndex;
    }
}