import java.util.*;

public class Dispatcher extends Gen 
{ //hej
    private double lambda;
    private ArrayList<QS> someSystems;
    private int sysIndex;

	public Dispatcher(ArrayList<QS> qs, double lambda)
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
                SignalList.SendSignal(ARRIVAL, someSystems.get(smartSys()), time);
                SignalList.SendSignal(READY, this, time + (2.0 / lambda) * slump.nextDouble());
            }
            break;
        }
    }

    public int rngSys() 
    {
        Random slump = new Random();
        int rngSys = slump.nextInt(5);
        return rngSys;
    }

    public int seqSys()
    {
        sysIndex = (sysIndex + 1) % someSystems.size();
        return sysIndex;
    }

    public int smartSys() 
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