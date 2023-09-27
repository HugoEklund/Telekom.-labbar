import java.io.*;

public class MainSimulation2 extends GlobalSimulation2{
 
    public static void main(String[] args) throws IOException 
	{
    	Event2 actEvent;
    	EventList2 myEventList = new EventList2();
    	State2 actState = new State2(myEventList);
        myEventList.InsertEvent(ARRIVAL_A, 0);
		myEventList.InsertEvent(ARRIVAL_B, 0);
        myEventList.InsertEvent(MEASURE, 5);
    	while (time < 105)
		{
    		actEvent = myEventList.FetchEvent();
    		time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
    	}
    	System.out.println("Mean number of customers in server + buffer: " + 0.1*actState.accumulated/actState.noMeasurements);
    	System.out.println("Number of measurements done: " + actState.noMeasurements);
    }
}