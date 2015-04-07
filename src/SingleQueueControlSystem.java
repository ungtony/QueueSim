import java.util.ArrayList;

/**
 * System that handles the generation of queues, servers, customers and the interaction between them for the simlulation 
 * @author Ben Lawton 
 *
 */

public class SingleQueueControlSystem implements QueueControlSystem {
	
	//The single queue in the simulation 
	private Queue queue; 
	
	//A collection of all of the servers in the simulation 
	private ServerCollection servers; 
	
	//The PersonFactory that generates the customers 
	private PersonFactory personFactory;
	
	//(Singleton pattern) The only instance of the class 
	private static SingleQueueControlSystem instance = null; 
	
	private SingleQueueControlSystem() {}
	
	//Instantiates the object if it hasn't already been instantiated. Otherwise just returns the lone object. 
	public static SingleQueueControlSystem getInstance() {
		if (instance == null) {
			instance = new SingleQueueControlSystem();
		}
		return instance; 
	}
	
	//Generates the queue for the simulation  
	public void generateQueueLayout() {
		queue = new PersonQueue();
	}
	
	//Generates the servers for the simulation 
	public void generateServers(int numServers) {
		servers = ServerCollection.getInstance();
		for (int i = 0; i < numServers; i++) {
			Server server = new HumanServer();
			servers.addServer(server);
		}
	}
	
	//If a customer is generated, adds it to the queue 
	public void customerArrival() {
		personFactory = PersonFactory.getInstance(); 
		Person newPerson = personFactory.generatePerson();
		if (newPerson != null) {
			queue.addPerson(newPerson);
		}
	}
	
	//Allocate a customer to all available servers 
	public void allocateCustomersToServers() {
		for (Server server : servers.showAvailableServers()) {
			server.setCurrentCustomer(queue.getHeadOfQueue());
			queue.removeHeadOfQueue();
			server.setFree(false);
		}
	}
	
	//Remove customers from the servers if their serve time has bee exceeded 
	public void removeCustomersFromServers() {
		for (Server server : servers.getServers()) {
			if (server.isFree() == false) {
				Person currentCustomer = server.getCurrentCustomer();
				if (currentCustomer.getServeTime() >= server.getTimeSpentServing()) {
					server.finishWithCustomer();
				}
			}
		}
	}

	public ArrayList<Queue> getQueues() {
		ArrayList<Queue> queueList = new ArrayList<Queue>();
		queueList.add(queue);
		return queueList; 
	}

}
