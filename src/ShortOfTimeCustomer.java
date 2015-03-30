/**
 * Class that models a customer who leaves the queue
 * if they're kept waiting for longer than 10 minutes (60 ticks)
 * @author Ben Lawton
 */

public class ShortOfTimeCustomer extends UnhappyCustomer{
	
	//Time spent waiting in the queue, measured in ticks 
	private int timeSpentQueueing;
	//The cutoff point where the customer becomes irritated and leaves
	private static final int PATIENCE_LIMIT = 60;
	
	public ShortOfTimeCustomer() {
		initialiseServeTime();
	}
	
	//Increment the time spent in the queue by one tick 
	public void incrementTimeSpentQueueing() {
		this.timeSpentQueueing++;
	}
	
	//Remove the object from the queue if patience limit is reached
	public void getUnhappyIfQueuedForTooLong() {
		if (this.timeSpentQueueing >= PATIENCE_LIMIT) {
			// TODO Method that removes the customer from the queue
		}
	}
	

}