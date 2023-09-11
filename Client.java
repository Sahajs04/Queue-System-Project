
public class Client 
{
    // The attributes of the class.
	private int id;
	private static int idCounter = 1;
    private String firstName;

    private String lastName;

    private int yearOfBirth;

    private Gender gender; 

    private Request[] requests;

    private int arrivalTime;

    private int waitingTime;

    private int timeInQueue;

    private int serviceTime; 

    private int departureTime;

    private int patience;

	private int startServed = 0;

	private int timeServed = 0;

	private boolean tvAndCoffe = false;

	// Getters and setters for the attributes of the class.
	public boolean getTvAndCoffe() {
		return this.tvAndCoffe;
	}

	public void setTvAndCoffe(boolean tvAndCoffe) {
		this.tvAndCoffe = tvAndCoffe;
	}

////////////////////////////////////////////////
	public int getTimeServed() {
		return this.timeServed;
	}

	public void setTimeServed(int timeServed) {
		this.timeServed = timeServed;
	}

//////////////////////////////////////////////////////
	public int getStartServed() {
		return this.startServed;
	}

	public void setStartServed(int startServed) {
		this.startServed = startServed;
	};

///////////////////////////////////////////////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public static void setIdCounter(int id)
	{
		idCounter = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYearOfBirth() {
		return this.yearOfBirth;
	}

	public void setYearOfBirth(int dateOfBirth) {
		this.yearOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Request[] getRequests() {
		return this.requests;

	}

	public void setRequests(Request[] requests) {
		this.requests = requests;
	}

	public int getArrivalTime() {
		return this.arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		
		this.arrivalTime = arrivalTime;
	}

	public int getWaitingTime() {
		return this.waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getTimeInQueue() {
		return this.timeInQueue;
	}

	public void setTimeInQueue(int timeInQueue) {
		this.timeInQueue = timeInQueue;
	}

	public int getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getDepartureTime() {
		return this.departureTime;
	}

	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}

	public int getPatience() {
		return this.patience;
	}

	public void setPatience(int patience) {
		this.patience = patience;
	}

///////////////////////////////////////////////////////////////////////////////////////
    



///////////////////////////////////////////////////////////////////////
// A constructor for the class Client.
public Client(String firstName, String lastName, int yearOfBirth, String gender,int arrivalTime,int patience, Request[] requests)
{
    
    this.firstName = firstName;

    this.lastName = lastName;

    this.yearOfBirth = yearOfBirth; 

    this.gender = Gender.valueOf(gender.toUpperCase());
    if(arrivalTime < 0)
    {
        this.arrivalTime = QueueSystem.getClock();
    }
    else
    {
    this.arrivalTime = arrivalTime; 
    }

    this.patience = patience;

    this.requests = requests;

    id = idCounter;
	idCounter++;

    waitingTime = 0;

    timeInQueue = 0; 

    serviceTime = 0;

    departureTime = 0;
}


// A constructor for the class Client.
public Client (String firstName, String lastName, int yearOfBirth, String gender,
int patience, Request[] requests)
{
    

    this.firstName = firstName; 

    this.lastName = lastName; 

    this.yearOfBirth = yearOfBirth; 

    this.gender = Gender.valueOf(gender.toUpperCase()); 

    this.patience = patience; 

    this.requests = requests;

    id = idCounter;
	idCounter++;
    arrivalTime = QueueSystem.getClock();

    waitingTime = 0; 

    timeInQueue = 0;

    serviceTime = 0;

    departureTime = 0;
}


// This is a constructor for the class Client.
public Client (String firstName, String lastName, int yearOfBirth, String gender,
int patience)
{
    

    this.firstName = firstName; 

    this.lastName = lastName; 

    this.yearOfBirth = yearOfBirth; 

    this.gender = Gender.valueOf(gender.toUpperCase()); 

    this.patience = patience; 

    id = idCounter;
	idCounter++;

    arrivalTime = QueueSystem.getClock();

    waitingTime = 0; 

    timeInQueue = 0;

    serviceTime = 0;

    departureTime = 0;
}
//////////////////////////////////////////////////////////
/**
 * This function returns the service time of the client
 * 
 * @return The service time of the client.
 */
public int serviceTime()
{
    return serviceTime; 

}

/**
 * This function estimates the service level of the client based on the time they spent in the queue
 * if the client has left returns -1
 * 
 * @return The service level of the client.
 */
public int estimateServiceLevel()
{
	
    int service = 10; 

    if(departureTime == 0)
	{
		return -1;
	}
	else 
	{
		if(timeInQueue > 12)
		{
			service -= 5;
		}
		else if(timeInQueue > 10)
		{
			service -= 4;
		}
		else if(timeInQueue > 8)
		{
			service -= 3;
		}
		else if(timeInQueue > 6)
		{
			service -= 2;
		}
		else if(timeInQueue > 4)
		{
			service -= 1;
		}
		if(waitingTime > 12)
		{
			service -= 5;
		}
		else if(waitingTime > 10)
		{
			service -= 4;
		}
		else if(waitingTime > 8 )
		{
			service -= 3;
		}
		else if(waitingTime > 6)
		{
			service -= 2;
		}
		else if(waitingTime > 4)
		{
			service -= 1;
		}
	}
	return service;

}


/**
 * This function is used to print out the information of a client
 * 
 * @return The client's last name, first name, arrival time, waiting time, time in queue, service time,
 * departure time, server name, and service level.
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
			if(client[j].getId() == id )
			{
				server = queues[i].getServerName();
			}
		}

	}


	String s = ("Client: " + lastName + ", " + firstName + "\n" + 
				"** Arrival Time    : " + arrivalTime + "\n" +
				"** Waiting Time    : " + waitingTime + "\n" +
				"** Time in Queue   : " + timeInQueue + "\n" +
				"** Service Time    : " + serviceTime + "\n" +
				"** Departure Time  : " + departureTime + "\n" +
				"** Served By Server: " + server + "\n" +
				"** Service Level   : " + estimateServiceLevel());

				return s;
				

}


/**
 * This function calculates the total processing time of all the requests in the array
 * 
 * @return The total processing time of all the requests in the array.
 */
public int totalProcessingTime()
{
    int total = 0;
    Request[] requests = getRequests();
	if(getRequests() != null)
    {
     requests = getRequests();
    }
    else{
         requests = null;
        return 0;
    }
     
    if(requests.length == 0)
    {
        return 0;
    }
    
    for(int i = 0; i < requests.length; i++)
    {
        total += requests[i].calculateProcessingTime();
    }
    return total;
}
///////////////////////////////////////////////

/**
 * This function calculates the total processing time of all the requests in the array and returns it
 * as a string
 * 
 * @return The total processing time of all the requests in the array.
 */
public String totalProcessingTimeString()
{
    int total = 0;
    Request[] requests;
    if(getRequests() != null)
    {
     requests = getRequests();
    }
    else{
         requests = null;
        return "" + 00;
    }
     
    if(requests.length == 0)
    {
        return "" + 00;
    }
    
    for(int i = 0; i < requests.length; i++)
    {   
        total += requests[i].calculateProcessingTime();
    }
    if(total < 10)
    {
        return 0 + "" + total;
    }
    else{
        return "" + total;
    }
}




}