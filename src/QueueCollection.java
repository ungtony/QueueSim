

import java.util.ArrayList;

/**
 * Represents a collection of many different queues.
 * @author Denver Fernandes
 *
 */

public class QueueCollection {
	
	/**
	 * The queues contained in this collection
	 */
	private ArrayList<Queue> queues;
	
	/**
	 * The instance
	 */
	public static QueueCollection instance = null;
	
	protected QueueCollection() {
		//To allow only a single point of access, the constructor is protected.
	}
	
	/**
	 * The object creation point.
	 * @return the instance
	 */
	public static QueueCollection getInstance() {
		if(instance == null) {
			instance = new QueueCollection();
		}
		return instance;
	}
	
	/**
	 * Returns the shortest queue.
	 * @return the shortest queue
	 */
	public Queue showShortestQueue() {
		/*TODO: Implement getting this method more efficiently. 
		The queue that is currently used as shortest by default may be null.*/
		int shortestLength = queues.get(0).getLength();
		Queue shortestQueue = null;
		for(Queue currentQueue : queues) {
			if(currentQueue.getLength() < shortestLength) {
				shortestQueue = currentQueue;
			}
		}
		return shortestQueue;
	}
	
	public void setQueues(ArrayList<Queue> queues) {
		this.queues = queues;
	}
	
}