import java.util.*;
import java.io.*;
 
public class MainSimulation extends Global
{
    public static void main(String[] args) throws IOException 
	{
        Signal actSignal;
        new SignalList();
		double totMean = 0;
		ArrayList<QS> aSysArray = new ArrayList<QS>();

		for (int i = 0; i < 5; i++)
		{
			aSysArray.add(i, new QS());
		}

		Dispatcher aDispatcher = new Dispatcher(aSysArray, 45);

		SignalList.SendSignal(READY, aDispatcher, time);

		for (QS i : aSysArray)
		{
			SignalList.SendSignal(MEASURE, i, time);
		}

        while (time < 100000)
		{
            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
        }

		for(int i = 0; i < aSysArray.size(); i++)
		{
			System.out.println("System: " + (i+1) + "  Mean: " +  1.0 * aSysArray.get(i).accumulated/aSysArray.get(i).noMeasurements);
			totMean += 1.0 * aSysArray.get(i).accumulated/aSysArray.get(i).noMeasurements;
		}
		System.out.println("Tot. Mean: " + totMean / aSysArray.size());
    }
}