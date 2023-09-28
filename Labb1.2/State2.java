import java.util.*;

class State2 extends GlobalSimulation2{
	
	public int a_Queue = 0, b_Queue = 0, accumulated = 0, noMeasurements = 0;
	
	private EventList2 myEventList;

	Random slump = new Random();
	
	State2(EventList2 x){
		myEventList = x;
	}
	
	private void InsertEvent(int event, double timeOfEvent)
	{
		myEventList.InsertEvent(event, timeOfEvent);
	}
	
	public void TreatEvent(Event2 x)
	{
		switch (x.eventType){
			case ARRIVAL_A:
				arrival_A();
				break;
			case ARRIVAL_B:
				arrival_B();
				break;
			case READY_A:
				ready_A();
				break;
			case READY_B:
				ready_B();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	private double generateMean(double mean)
	{
		return 2*mean*slump.nextDouble();
	}
	
	public double arrivalInterval() 
	{
		double nbr = slump.nextDouble(2.0/150.0);
		return nbr;
	}
	
	private void arrival_A()
	{
		if (b_Queue == 0)
		{
			InsertEvent(READY_A, time + 0.002);
			a_Queue++;
			InsertEvent(ARRIVAL_A, time + generateMean(arrivalInterval()));
		}
	}
	
	private void arrival_B() 
	{
		if (a_Queue == 0 && b_Queue == 0)
		{
			InsertEvent(READY_B, time + 0.004);
			b_Queue++;
		}
	}
	
	private void ready_A()
	{
		a_Queue--;
		if (b_Queue > 0)
		{
			InsertEvent(READY_A, time + 0.002);
		}
		else if(a_Queue > 0)
		{
			InsertEvent(READY_B, time + 0.004);
		}
		InsertEvent(ARRIVAL_B, time + 1);
	}

	private void ready_B()
	{
		b_Queue--;
		if (b_Queue > 0)
		{
			InsertEvent(READY_B, time + 0.002);
		}
		else if(a_Queue > 0)
		{
			InsertEvent(READY_A, time + 0.004);
		}
		InsertEvent(ARRIVAL_B, time + 1);
	}
	
	private void measure()
	{
		accumulated += a_Queue + b_Queue;
		noMeasurements++;
		InsertEvent(MEASURE, time + 0.1);
	}
}