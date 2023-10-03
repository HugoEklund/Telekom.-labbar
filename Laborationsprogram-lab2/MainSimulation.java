import java.util.*;
import java.io.*;
 
public class MainSimulation extends Global
{
    public static void main(String[] args) throws IOException 
	{
        Signal actSignal;
        new SignalList();
		double totMean = 0;
		ArrayList<QS> queueArray = new ArrayList<QS>();

		for (int i = 0; i < 5; i++)
		{
			queueArray.add(i, new QS());
		}

		Dispatcher tempDis = new Dispatcher(queueArray, 45);

		SignalList.SendSignal(READY, tempDis, time);

		for (QS i : queueArray)
		{
			SignalList.SendSignal(MEASURE, i, time);
		}

        while (time < 100000)
		{
            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
        }

		for(int i = 0; i < queueArray.size(); i++)
		{
			System.out.println("Queue: " + (i+1) + "  Mean: " +  1.0 * queueArray.get(i).accumulated/queueArray.get(i).noMeasurements);
			totMean += 1.0 * queueArray.get(i).accumulated/queueArray.get(i).noMeasurements;
		}
		System.out.println("Tot. Mean: " + totMean / queueArray.size());
    }
}