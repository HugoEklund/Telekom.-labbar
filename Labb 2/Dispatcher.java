import java.util.*;

public class Dispatcher extends Gen 
{
    public double lambda;
    public ArrayList<QS> sendToQueue;

	public Dispatcher(ArrayList<QS> qs, double lambda)
	{
        this.sendToQueue = qs;
        this.lambda = lambda;
	}
    
    public void TreatSignal(Signal x) 
    {
        switch (x.signalType) 
        {
            case READY:
            {
                Random slump = new Random();
                int rngQueue = slump.nextInt(5);
                SignalList.SendSignal(ARRIVAL, sendToQueue.get(rngQueue), time);
                SignalList.SendSignal(READY, this, time + (2.0 / lambda) * slump.nextDouble());
            }
            break;
        }
    }
}

//hej