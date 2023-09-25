import java.util.*;

class State extends GlobalSimulation{
	
	public int numberInQueue = 0, accumulated = 0, noMeasurements = 0;
	
	private EventList myEventList;

	Random slump = new Random();
	
	State(EventList x){
		myEventList = x;
	}
	
	private void InsertEvent(int event, double timeOfEvent){
		myEventList.InsertEvent(event, timeOfEvent);
	}
	
	
	public void TreatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL:
				arrival();
				break;
			case READY:
				ready();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	private double generateMean(double mean){
		return 2*mean*slump.nextDouble();
	}
	
	private void arrival(){
		if (numberInQueue == 0)
			InsertEvent(READY, time +1);
		numberInQueue++;
		InsertEvent(ARRIVAL, time + 1/0.98);
	}
	
	private void ready(){
		numberInQueue--;
		if (numberInQueue > 0)
			InsertEvent(READY, time + generateMean(1));
	}
	
	private void measure(){
		accumulated = accumulated + numberInQueue;
		noMeasurements++;
		InsertEvent(MEASURE, time + generateMean(5));
	}
}