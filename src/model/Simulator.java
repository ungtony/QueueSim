package model;
/**
 * The main class the runs the simulator.
 * @author Denver Fernandes
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import view.impl.AnimatedView;


public class Simulator {
	
	public static int TICK = 10;
	public static int NUM_OF_SERVERS = 3;
	//Length of the simulation, should equal 4 hours in simulated time 
	public static int LENGTH_OF_SIMULATION = 1440;
	public static boolean SHOULD_RUN = true;
	public static QueueControlSystem SELECTEDQUEUESYSTEM = AnimatedView.selectedQueueControlSystem;
	public static void main(String[] args) {
		
		SELECTEDQUEUESYSTEM.generateQueuesAndServers(NUM_OF_SERVERS);
		
        final ScheduledExecutorService ticker = Executors.newSingleThreadScheduledExecutor();
        
        ticker.scheduleAtFixedRate(new Runnable() {
        	
    		int currentTime = 0;
        	
			public void run() {
				if(!SHOULD_RUN) {
					ticker.shutdown();
					return;
				}
				if(currentTime < LENGTH_OF_SIMULATION) {
					currentTime = currentTime + 1;
				} else {
					ticker.shutdown();
					return;
				}
				
				SELECTEDQUEUESYSTEM.customerArrival();
				SELECTEDQUEUESYSTEM.allocateCustomersToServers();
	            //Need to serve customers once allocated
				SELECTEDQUEUESYSTEM.serveAndFinishWithCustomers();
	            	
				SELECTEDQUEUESYSTEM.customerArrival();
				SELECTEDQUEUESYSTEM.allocateCustomersToServers();
	            //Need to serve customers once allocated
				SELECTEDQUEUESYSTEM.serveAndFinishWithCustomers();
			}
		}, 1, TICK, TimeUnit.MILLISECONDS);
	}
}

