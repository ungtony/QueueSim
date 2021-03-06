package model;


/**
 * An abstract class to represent a Server.
 * @author Denver Fernandes
 * @author Ben Lawton
 */
public abstract class Server {
	
	//The current customer the server is serving.
	private Person currentCustomer;

	//A flag to check whether the server is free.
	private boolean isFree;
	
	//Time spent serving the current customer 
	private int timeSpentServing;
	
	//If in a multi-queue system, the queue allocated to the server
	private Queue allocatedQueue;
	
	public Server() {
		currentCustomer = null;
		isFree = true;
		timeSpentServing = 0;
	}
	
	/**
	 * Sets the {@link #currentCustomer} and {@link #isFree()} variables to their default value.
	 * This indicates that the server is free.
	 */
	public void finishWithCustomer() {
		currentCustomer = null;
		isFree = true;
		timeSpentServing = 0;
		Stats.CUSTOMERS_SERVED++;
	}
	
	/**
	 * Serves the customer.
	 */
	public void serveCustomer() {
		if (currentCustomer != null) {
			timeSpentServing++;
		}
	}
	
	/**
	 * Shows the availability of the server.
	 * @return whether the server is free or not
	 */
	public boolean showAvailability() {
		return ((isFree) && (currentCustomer == null));
	}
	
	/**
	 * Gets the current customer being server.
	 * @return the current customer being server
	 */
	public Person getCurrentCustomer() {
		return currentCustomer;
	}
	
	/**
	 * Sets the current customer being served.
	 * @param currentCustomer the customer being served.
	 */
	public void setCurrentCustomer(Person currentCustomer) {
		this.currentCustomer = currentCustomer;
	}
	
	public boolean isFree() {
		return isFree;
	}
	
	/**
	 * Sets the flag to indicate whether the server is free or not.
	 * @param isFree whether the server is free or not
	 */
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	public int getTimeSpentServing() {
		return this.timeSpentServing;
	}
	
	public Queue getAllocatedQueue() {
		return this.allocatedQueue;
	}
	
	public void setAllocatedQueue(Queue queue) {
		this.allocatedQueue = queue; 
	}

}
