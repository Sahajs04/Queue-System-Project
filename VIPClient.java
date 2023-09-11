public class VIPClient extends Client implements Prioritizable
{
// This is declaring the memberSince and priority variables.
    private int memberSince;

    private int priority; 

    ////////////////////////////////////////////////
// This is a getter and setter for the memberSince and priority variables.
	public int getMemberSince() {
		return this.memberSince;
	}

	public void setMemberSince(int memberSince) {
		this.memberSince = memberSince;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
    /////////////////////////////////////////////////////

    // This is the constructor for the VIPClient class. It is calling the super class constructor and
	// setting the memberSince and priority variables.
	public VIPClient (String firstName, String lastName, int birthYear, String gender,
int arrivalTime, int patience, Request[] requests, int memberSince, int priority)
    {
       super(firstName, lastName, birthYear, gender, arrivalTime, patience, requests);

	   this.memberSince = memberSince;
	   this.priority = priority;



    }

	//////////////////////////////////////////////////////////////////
	/**
	 * This function is used to print out the vip client's information
	 * 
	 * @return The toString method is returning a string that contains the client's last name, first name,
	 * arrival time, waiting time, time in queue, service time, departure time, server name, service
	 * level, member since, and priority.
	 */
	public String toString()
	{
		String server = "";
	Queue[] queues = QueueSystem.getQueues();
	for(int i = 0; i < QueueSystem.getQueues().length; i++)
	{
		Client[] client = queues[i].getClientsHistory();
		for(int j = 0; j < client.length; j++)
		{
			if(client[j].getId() == getId() )
			{
				server = queues[i].getServerName();
			}
		}

	}
		String s = ("Client: " + getLastName() + ", " + getFirstName() + "\n" + 
				"** Arrival Time    : " + getArrivalTime() + "\n" +
				"** Waiting Time    : " + getWaitingTime() + "\n" +
				"** Time in Queue   : " + getTimeInQueue() + "\n" +
				"** Service Time    : " + getServiceTime() + "\n" +
				"** Departure Time  : " + getDepartureTime() + "\n" +
				"** Served By Server: " + server + "\n" +
				"** Service Level   : " + estimateServiceLevel() + "\n" +
				"** VIP since       : " + memberSince + "\n" +
				"** priority        : " + priority); 

				return s;
   	}
	//////////////////////////////////////////////////////////////////////////
}
