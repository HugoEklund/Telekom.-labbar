import java.util.*;

class State2 extends GlobalSimulation2{
	
	public int nbrInQA = 0, nbrInQB = 0, accumulated = 0, noMeasurements = 0;
	
	private EventList2 myEventList;

	Random slump = new Random();
	
	State2(EventList2 x){
		myEventList = x;
	}
	
	private void InsertEvent(int event, double timeOfEvent){
		myEventList.InsertEvent(event, timeOfEvent);
	}
	
	
	public void TreatEvent(Event2 x){
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
	
	private double generateMean(double mean){
		return 2*mean*slump.nextDouble();
	}
	
	public double intervall() {
		double nbr = slump.nextDouble(0.2 , 150);
		return 1/nbr;
	}
	
	private void arrival_A(){
		if (nbrInQA == 0 && nbrInQB == 0)
			InsertEvent(READY_A, time + 0.002); // betjäning alltid 2 ms
		nbrInQA++;
		InsertEvent(ARRIVAL_A, time + generateMean(intervall())); // inkomst 1/150 ≈ 0.00667
	}
	
	private void arrival_B() {
		if(nbrInQB == 0) { 
			InsertEvent(READY_B, time + 0.004); // betjäning alltid 4 ms
		nbrInQB++;
		InsertEvent(ARRIVAL_B, time + 1); // inkomst alltid 1 
		}
	}
	
	private void ready_A(){
		nbrInQA--;
		if (nbrInQA > 0)
			InsertEvent(READY_A, time + 1); //förbindelse alltid 1s
	}
	
	private void ready_B() {
		nbrInQB--;
		if(nbrInQB > 0)
			InsertEvent(READY_B, time + 1); //förbindelse alltid 1s
	}
	
	private void measure(){
		accumulated += nbrInQA + nbrInQB;
		noMeasurements++;
		InsertEvent(MEASURE, time + 0.1);
	}
}