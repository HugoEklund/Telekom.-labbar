import java.io.*;

public class MainSimulation2 extends GlobalSimulation{
 
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	EventList myEventList = new EventList();
    	State actState = new State(myEventList);
        myEventList.InsertEvent(ARRIVAL, 0);
        myEventList.InsertEvent(MEASURE, 5);
    	while (time < 50000){
    		actEvent = myEventList.FetchEvent();
    		time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	System.out.println("Mean number of customers: " + 1.0*actState.accumulated/actState.noMeasurements);
    	System.out.println("Number of measurements done: " + actState.noMeasurements);
    }
}