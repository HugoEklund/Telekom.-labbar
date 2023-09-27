public class EventList2
{
	private Event2 list, last;
	
	EventList2(){
		list = new Event2();
    	last = new Event2();
    	list.next = last;
	}
	
	public void InsertEvent(int type, double TimeOfEvent){
 	Event2 dummy, predummy;
 	Event2 newEvent = new Event2();
 	newEvent.eventType = type;
 	newEvent.eventTime = TimeOfEvent;
 	predummy = list;
 	dummy = list.next;
 	while ((dummy.eventTime < newEvent.eventTime) & (dummy != last)){
 		predummy = dummy;
 		dummy = dummy.next;
 	}
 	predummy.next = newEvent;
 	newEvent.next = dummy;
 }
	
	public Event2 FetchEvent(){
		Event2 dummy;
		dummy = list.next;
		list.next = dummy.next;
		dummy.next = null;
		return dummy;
	}
}
