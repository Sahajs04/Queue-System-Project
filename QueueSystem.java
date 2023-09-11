
public class QueueSystem 
{

    // Instance variables
    private static int clock;

    private static int totalWaitingTime;

    private static Client[] clientsWorld;

    private static int totalClientsInSystem;

    private static int waitingLineSize;

    private static Client[] waitingLine;

    private static boolean tvInWaitingArea;

    private static boolean coffeeInWaitingArea;

    private static Queue[] queues;
    /////////////////////////////////////////////
    // The below code is a set of getters and setters for the variables in the class.
    public static int getClock() {
        return clock;
    }

    public static void setClock(int clock1) {
        clock = clock1;
    }

    public static int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public static void setTotalWaitingTime(int totalWaitingTime1) {
        totalWaitingTime = totalWaitingTime1;
    }

    public static Client[] getClientsWorld() {
        return clientsWorld;
    }

    public static void setClientsWorld(Client[] clientsWorld1) {
        clientsWorld = clientsWorld1;
    }

    public static int getTotalClientsInSystem() {
        return totalClientsInSystem;
    }

    public static void setTotalClientsInSystem(int totalClientsInSystem1) {
        totalClientsInSystem = totalClientsInSystem1;
    }

    public static int getWaitingLineSize() {
        return waitingLineSize;
    }

    public static void setWaitingLineSize(int waitingLineSize1) {
        waitingLineSize = waitingLineSize1;
    }

    public static Client[] getWaitingLine() {
        return waitingLine;
    }

    public static void setWaitingLine(Client[] waitingLine1) {
        waitingLine = waitingLine1;
    }

    public static boolean isTvInWaitingArea() {
        return tvInWaitingArea;
    }

    public static void setTvInWaitingArea(boolean tvInWaitingArea1) {
        tvInWaitingArea = tvInWaitingArea1;
    }

    public static boolean isCoffeeInWaitingArea() {
        return coffeeInWaitingArea;
    }

    public static void setCoffeeInWaitingArea(boolean coffeeInWaitingArea1) {
        coffeeInWaitingArea = coffeeInWaitingArea1;
    }

    public static Queue[] getQueues() {
        return queues;
    }

    public static void setQueues(Queue[] queues1) {
        queues = queues1;
    }
    ///////////////////////////////////////////////////////////////////////
    // Check updated constructor 
    // Creating a constructor for the QueueSystem class.
    public QueueSystem (int waitingLineSize1, boolean tvInWaitingArea1, boolean coffeeInWaitingArea1)  
    {
        clock = 0;

        waitingLineSize = waitingLineSize1; 

        waitingLine = new Client[waitingLineSize1];
        
        waitingLine = new Client[waitingLineSize1];

        tvInWaitingArea = tvInWaitingArea1;

        coffeeInWaitingArea = coffeeInWaitingArea1;
        /*
        if(tvInWaitingArea == true)
        {
            for(int i = 0; i < waitingLine.length; i++ )
            {
                waitingLine[i].setPatience(waitingLine[i].getPatience() + 20);
            }
        }
        
        if((coffeeInWaitingArea == true))
        {
            for(int j = 0; j < waitingLine.length; j++)
            {
                if(2023 - waitingLine[j].getYearOfBirth() >= 18)
                {
                    waitingLine[j].setPatience(waitingLine[j].getPatience() + 15);
                }
            }
        }
        */
    }

///////////////////////////////////////////////////////////////////////////////

 public void increaseTime()
    {
        
        clock += 1;
        Client beingServed;
        int completion;
        Request[] requests;
        Client[] history;
        int count = 0;
        
///////////////////////////////////////////////////////////////////////////////////////
//checking wether to kick person out of line based on patience
for(int i = 0; i < waitingLine.length; i++)
{
    if(waitingLine[i] != null)
    {

    if(waitingLine[i].getPatience() < clock - waitingLine[i].getArrivalTime())
    {
        
        waitingLine[i] = null;
        sortQueue();
    }
    }
}
for(int i = 0; i < queues.length; i++)
{
    for(int j = 0; j < queues[i].getClientsInQueue().length; j++)
    {
        if(queues[i].getClientsInQueue()[j] != null)
        {
         
    if(queues[i].getClientsInQueue()[j].getPatience() < clock - queues[i].getClientsInQueue()[j].getArrivalTime())
    {
        queues[i].getClientsInQueue()[j] = null;
        sortQueue();
    }
    }
    }
}
//////////////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////////////////////////////
//move people directly to clients being served from either the queue the waiting line or from clients world
        for(int i = 0; i < queues.length; i++)
        {
            if(queues[i].getClientBeingServed() == null)
            {
                Queue temp = queues[i];
                if(temp.getClientsInQueue()[0] != null)
                {
                    queues[i].setClientBeingServed(temp.getClientsInQueue()[1]);
                    Client[] clients = temp.getClientsInQueue();
                    clients[0] = null;
                    temp.setClientsInQueue(clients);
                    sortQueue();
                }
                else if(waitingLine[0] != null)
                {
                    queues[i].setClientBeingServed(waitingLine[0]);
                    waitingLine[0] = null;
                    sortQueue();
                }
                else
                {
                    int arrival = clientsWorld[0].getArrivalTime();
                    int id = clientsWorld[0].getId();
                    Client max = clientsWorld[0];
                    for(int j = 1; j < clientsWorld.length; j++)
                    {
                        if(clientsWorld[i] != null)
                        {
                            if(clientsWorld[i].getArrivalTime() < arrival)
                            {
                                id = clientsWorld[i].getId();
                                arrival = clientsWorld[i].getArrivalTime();
                                max = clientsWorld[i];
                            }
                            else if(clientsWorld[i].getArrivalTime() == arrival)
                            {
                                if(clientsWorld[i].getId() < id)
                                {
                                    id = clientsWorld[i].getId();
                                    arrival = clientsWorld[i].getArrivalTime();
                                    max = clientsWorld[i];
                                }
                            }
                        }
                    }
                    if(max.getArrivalTime() <= clock)
                    queues[i].setClientBeingServed(max);
                }
            }
        }

     
///////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////
        for(int i = 0; i < queues.length; i++)
        {
            //////////iF CLIENT BEING SERVED IS NULL
            if(queues[i].getClientBeingServed() == null)
            {
                continue;
            }
            ///////////////IF REQUESTS IS 0 OR NULL
            if((queues[i].getClientBeingServed().getRequests() == null) || queues[i].getClientBeingServed().getRequests().length == 0)
            {
                Client finished = queues[i].getClientBeingServed();
                queues[i].setClientBeingServed(null);
                history = queues[i].getClientsHistory();
                Client[] temp = new Client[history.length + 1];
                for(int k = 0; k < history.length; k++ )
                {
                    temp[k] = history[k];
                }
                temp[temp.length -1] = finished;
                queues[i].setClientsHistory(history); 
            //////////////////////////////////////////////////////////////////////
            //check if client beingServed first request is new then set it to in progress
            }
            if(queues[i].getClientBeingServed() != null)
            {
                beingServed = queues[i].getClientBeingServed();
                requests = beingServed.getRequests();

                if(requests[0].getStatus().equals(Status.NEW))
                {   
                    requests[0].setStartTime(clock);
                    requests[0].setEndTime(requests[0].getStartTime() + requests[0].calculateProcessingTime());
                    requests[0].setStatus(Status.IN_PROGRESS);
                    completion = 0;
                    continue;
                }

               for(int j = 0; j < requests.length; j++)
               {
                if(requests[j].getStatus().equals(Status.IN_PROGRESS))
                {
                    completion = ((clock - requests[i].getStartTime()) * 100)/ (requests[j].getEndTime() - requests[j].getStartTime());
                    if(completion >= 100)
                    {
                        requests[j].setStatus(Status.PROCESSED);
                        if(j + 1 < requests.length)
                        {
                            requests[j+1].setStatus(Status.IN_PROGRESS);
                            requests[j+1].setStartTime(clock);
                            requests[j+1].setEndTime(requests[j+1].getStartTime() + requests[j+1].calculateProcessingTime());
                            completion = 0;
                            requests[j+1].setCompletionLevel(completion);

                        }
                        else
                        {
                            queues[i].getClientBeingServed().setDepartureTime(clock);
                            history = queues[i].getClientsHistory();
                            if((history.length == 0) || history == null)
                            {
                                Client[] temp = new Client[1];
                                temp[0] = queues[i].getClientBeingServed();
                                queues[i].setClientsHistory(temp);
                                queues[i].setClientBeingServed(null);
                            }
                            else
                            {
                            Client[] temp = new Client[history.length + 1];
                            for(int t = 0; t < temp.length -1; t++)
                            {
                                temp[t] = history[t];
                            }
                            temp[history.length] = queues[i].getClientBeingServed();
                            queues[i].setClientBeingServed(null);
                        }
                        }
                    }
                    else{
                    requests[j].setCompletionLevel(completion);
                    beingServed.setTimeServed(beingServed.getTimeServed() + 1);
                    }
                }
               }
                
            }
        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
//set the waiting time/ time in queue/ service time variables.
for(int i = 0; i < waitingLine.length; i++)
{
    if(waitingLine[i] != null)
    {
        waitingLine[i].setWaitingTime(waitingLine[i].getWaitingTime() +1);
    }
}
for(int i = 0; i < queues.length; i++)
{
    for(int j = 0; j < queues[i].getClientsInQueue().length; j++)
    {
        if(queues[i].getClientsInQueue()[j] != null)
        {
            queues[i].getClientsInQueue()[j].setTimeInQueue(queues[i].getClientsInQueue()[j].getTimeInQueue() +1); 
        }
    }
}
for(int i = 0; i < queues.length; i++)
{
    if(queues[i].getClientBeingServed() != null)
    {
        queues[i].getClientBeingServed().setServiceTime(queues[i].getClientBeingServed().totalProcessingTime());
    }
}
//checks if person in waitingline has already had patience increased from tv and cofee if not increase
        for(int i = 0;i < waitingLine.length; i++)
        {
            if(waitingLine[i] != null)
            {
            if(waitingLine[i].getTvAndCoffe() == false)
            {
                waitingLine[i].setTvAndCoffe(true);
                int age = 2023 - waitingLine[i].getYearOfBirth();
                if((age >= 18)&& (coffeeInWaitingArea == true))
                {
                    waitingLine[i].setPatience(waitingLine[i].getPatience() + 15);
                }
                if(tvInWaitingArea == true)
                {
                    waitingLine[i].setPatience(waitingLine[i].getPatience() + 20);
                }
            }
         }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////
//move people from clients world to queue and waiting line and waiting line to queue
//could only implement moving people to waiting line
//so that is why commented out
/*
for (int i = 0; i < clientsWorld.length -1; i++) 
{
    for (int j = i + 1; j < clientsWorld.length; j++)
     {
        if (clientsWorld[i].getArrivalTime() < clientsWorld[j].getArrivalTime()) 
        {
            Client client = clientsWorld[i];
            clientsWorld[i] = clientsWorld[j];
            clientsWorld[j] = client;
        }
        else if(clientsWorld[i].getArrivalTime() == clientsWorld[j].getArrivalTime())
        {
            if(clientsWorld[i].getId() < clientsWorld[j].getId())
            {
                Client client = clientsWorld[i];
                clientsWorld[i] = clientsWorld[j];
                clientsWorld[j] = client;
            }
            else
            {
                Client client = clientsWorld[j];
                clientsWorld[j] = clientsWorld[i];
                clientsWorld[i] = client;
            }
        }
    }
}
for(int i = 0; i < clientsWorld.length; i++)
{
    if(clientsWorld[i] != null)
    {
    if(clientsWorld[i].getArrivalTime() <= clock)
    {
        for(int j = 0; j < waitingLine.length; j++ )
        {
            if(waitingLine[j] == null)
            {
                waitingLine[j] = clientsWorld[i];
                clientsWorld[i] = null;
            }
        }
    }
}
}   
*/  
 }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void increaseTime(int time)
    {

        clock += time;
        Client beingServed;
        int completion;
        Request[] requests;
        Client[] history;
        int count = 0;
        
///////////////////////////////////////////////////////////////////////////////////////
//checking wether to kick person out of line based on patience
for(int i = 0; i < waitingLine.length; i++)
{
    if(waitingLine[i] != null)
    {

    if(waitingLine[i].getPatience() < clock - waitingLine[i].getArrivalTime())
    {
        
        waitingLine[i] = null;
        sortQueue();
    }
    }
}
for(int i = 0; i < queues.length; i++)
{
    for(int j = 0; j < queues[i].getClientsInQueue().length; j++)
    {
        if(queues[i].getClientsInQueue()[j] != null)
        {
         
    if(queues[i].getClientsInQueue()[j].getPatience() < clock - queues[i].getClientsInQueue()[j].getArrivalTime())
    {
        queues[i].getClientsInQueue()[j] = null;
        sortQueue();
    }
    }
    }
}
//////////////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////////////////////////////
//move people directly to clients being served from either the queue the waiting line or from clients world
        for(int i = 0; i < queues.length; i++)
        {
            if(queues[i].getClientBeingServed() == null)
            {
                Queue temp = queues[i];
                if(temp.getClientsInQueue()[0] != null)
                {
                    queues[i].setClientBeingServed(temp.getClientsInQueue()[1]);
                    Client[] clients = temp.getClientsInQueue();
                    clients[0] = null;
                    temp.setClientsInQueue(clients);
                    sortQueue();
                }
                else if(waitingLine[0] != null)
                {
                    queues[i].setClientBeingServed(waitingLine[0]);
                    waitingLine[0] = null;
                    sortQueue();
                }
                else
                {
                    int arrival = clientsWorld[0].getArrivalTime();
                    int id = clientsWorld[0].getId();
                    Client max = clientsWorld[0];
                    for(int j = 1; j < clientsWorld.length; j++)
                    {
                        if(clientsWorld[i] != null)
                        {
                            if(clientsWorld[i].getArrivalTime() > arrival)
                            {
                                id = clientsWorld[i].getId();
                                arrival = clientsWorld[i].getArrivalTime();
                                max = clientsWorld[i];
                            }
                            else if(clientsWorld[i].getArrivalTime() == arrival)
                            {
                                if(clientsWorld[i].getId() < id)
                                {
                                    id = clientsWorld[i].getId();
                                    arrival = clientsWorld[i].getArrivalTime();
                                    max = clientsWorld[i];
                                }
                            }
                        }
                    }
                    queues[i].setClientBeingServed(max);
                }
            }
        }

     
///////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////
//processing requests
        for(int i = 0; i < queues.length; i++)
        {
            //////////iF CLIENT BEING SERVED IS NULL
            if(queues[i].getClientBeingServed() == null)
            {
                continue;
            }
            ///////////////IF REQUESTS IS 0 OR NULL
            if((queues[i].getClientBeingServed().getRequests() == null) || queues[i].getClientBeingServed().getRequests().length == 0)
            {
                Client finished = queues[i].getClientBeingServed();
                queues[i].setClientBeingServed(null);
                history = queues[i].getClientsHistory();
                Client[] temp = new Client[history.length + 1];
                for(int k = 0; k < history.length; k++ )
                {
                    temp[k] = history[k];
                }
                temp[temp.length -1] = finished;
                queues[i].setClientsHistory(history); 
            //////////////////////////////////////////////////////////////////////
            //check if client beingServed first request is new then set it to in progress
            }
            if(queues[i].getClientBeingServed() != null)
            {
                beingServed = queues[i].getClientBeingServed();
                requests = beingServed.getRequests();

                if(requests[0].getStatus().equals(Status.NEW))
                {   
                    requests[0].setStartTime(clock);
                    requests[0].setEndTime(requests[0].getStartTime() + requests[0].calculateProcessingTime());
                    requests[0].setStatus(Status.IN_PROGRESS);
                    completion = 0;
                    continue;
                }

               for(int j = 0; j < requests.length; j++)
               {
                //if there is a request in progress
                if(requests[j].getStatus().equals(Status.IN_PROGRESS))
                {
                    completion = ((clock - requests[i].getStartTime()) * 100)/ (requests[j].getEndTime() - requests[j].getStartTime());
                    if(completion >= 100)
                    {
                        requests[j].setStatus(Status.PROCESSED);
                        if(j + 1 < requests.length)
                        {
                            requests[j+1].setStatus(Status.IN_PROGRESS);
                            requests[j+1].setStartTime(clock);
                            requests[j+1].setEndTime(requests[j+1].getStartTime() + requests[j+1].calculateProcessingTime());
                            completion = 0;
                            requests[j+1].setCompletionLevel(completion);

                        }
                        else
                        {
                            //if there are no requests left then add client to client history
                            queues[i].getClientBeingServed().setDepartureTime(clock);
                            history = queues[i].getClientsHistory();
                            if((history.length == 0) || history == null)
                            {
                                Client[] temp = new Client[1];
                                temp[0] = queues[i].getClientBeingServed();
                                queues[i].setClientsHistory(temp);
                                queues[i].setClientBeingServed(null);
                            }
                            else
                            {
                            Client[] temp = new Client[history.length + 1];
                            for(int t = 0; t < temp.length -1; t++)
                            {
                                temp[t] = history[t];
                            }
                            temp[history.length] = queues[i].getClientBeingServed();
                            queues[i].setClientBeingServed(null);
                        }
                        }
                    }
                    else{
                    requests[j].setCompletionLevel(completion);
                    beingServed.setTimeServed(beingServed.getTimeServed() + time);
                    }
                }
               }
                
            }
        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
//move people from clients world to queue and waiting line and waiting line to queue
//only could get moving people from clients world to waiting line so thats why it is commented out
/* 
for (int i = 0; i < clientsWorld.length -1; i++) 
{
    for (int j = i + 1; j < clientsWorld.length; j++)
     {
        if (clientsWorld[i].getArrivalTime() < clientsWorld[j].getArrivalTime()) 
        {
            Client client = clientsWorld[i];
            clientsWorld[i] = clientsWorld[j];
            clientsWorld[j] = client;
        }
        else if(clientsWorld[i].getArrivalTime() == clientsWorld[j].getArrivalTime())
        {
            if(clientsWorld[i].getId() < clientsWorld[j].getId())
            {
                Client client = clientsWorld[i];
                clientsWorld[i] = clientsWorld[j];
                clientsWorld[j] = client;
            }
            else
            {
                Client client = clientsWorld[j];
                clientsWorld[j] = clientsWorld[i];
                clientsWorld[i] = client;
            }
        }
    }
}
for(int i = 0; i < clientsWorld.length; i++)
{
    if(clientsWorld[i] != null)
    {
    if(clientsWorld[i].getArrivalTime() <= clock)
    {
        for(int j = 0; j < waitingLine.length; j++ )
        {
            if(waitingLine[j] == null)
            {
                waitingLine[j] = clientsWorld[i];
                clientsWorld[i] = null;
            }
        }
    }
}
}  
 
*/

/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
//set the waiting time/ time in queue/ service time variables.
for(int i = 0; i < waitingLine.length; i++)
{
    if(waitingLine[i] != null)
    {
        waitingLine[i].setWaitingTime(waitingLine[i].getWaitingTime() +1);
    }
}
for(int i = 0; i < queues.length; i++)
{
    for(int j = 0; j < queues[i].getClientsInQueue().length; j++)
    {
        if(queues[i].getClientsInQueue()[j] != null)
        {
            queues[i].getClientsInQueue()[j].setTimeInQueue(queues[i].getClientsInQueue()[j].getTimeInQueue() +1); 
        }
    }
}
for(int i = 0; i < queues.length; i++)
{
    if(queues[i].getClientBeingServed() != null)
    {
        queues[i].getClientBeingServed().setServiceTime(queues[i].getClientBeingServed().totalProcessingTime());
    }
}
//checks if person in waitingline has already had patience increased from tv and cofee if not increase
        for(int i = 0;i < waitingLine.length; i++)
        {
            if(waitingLine[i] != null)
            {
            if(waitingLine[i].getTvAndCoffe() == false)
            {
                waitingLine[i].setTvAndCoffe(true);
                int age = 2023 - waitingLine[i].getYearOfBirth();
                if((age >= 18)&& (coffeeInWaitingArea == true))
                {
                    waitingLine[i].setPatience(waitingLine[i].getPatience() + 15);
                }
                if(tvInWaitingArea == true)
                {
                    waitingLine[i].setPatience(waitingLine[i].getPatience() + 20);
                }
            }
         }
    }

    }
 ////////////////////////////////////////////////////////////////////////////////////

    //returns all the clients being served
    public Client[] getClientsBeingServed()
    {
        Client[] served = new Client[queues.length];

        for(int i = 0; i < served.length; i++)
        {
            served[i] = queues[i].getClientBeingServed();
        }
        return served;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
   
   
   //puts the queuesystem information in a string
    public String toString()
    {
        return toString(false);
    }
    public String toString(boolean showID)
    {
        String s = "";

        for(int i = 0; i < queues.length; i++)
        {
            s+= queues[i].toString(showID) + "\n";
        }
        s+="[WaitingLine]-";
        for(int i = 0; i <waitingLine.length; i++)
        {
            if(waitingLine[i] != null)
            {
            if(showID == false)
            {
            s+="[" + waitingLine[i].totalProcessingTimeString() + "]";
            }
            else 
            {
                s+= "[" + waitingLine[i].getId() + "]";
            }
        }
        else
        {
            s+= "[  ]";
        }
         }
        return s;
    }

///////////////////////////////////////////////
//fills in the null spaces in the queue and waitingline and moves the null spaces to the back
public void sortQueue()
{
    for(int i = 0; i < queues.length; i++)
    {
        Client[] inqueue = queues[i].getClientsInQueue();
        for(int j = 0; j < queues[i].getClientsInQueue().length; j++)
        {
            if(inqueue[j] == null)
            {
               int index = j;
                for(int t = index + 1; t < inqueue.length; t++)
                {
                    if(inqueue[t] != null)
                    {
                        Client temp = inqueue[t];
                        inqueue[t] = null;
                        inqueue[j] = temp;

                    }
                }
            }
        }
        queues[i].setClientsInQueue(inqueue);
    }
    ///////////////////////////////////////////////
    for(int i = 0; i < waitingLine.length; i++)
    {
        for(int j = 0; j < waitingLine.length; j++)
        {
            if(waitingLine[j] == null)
            {
               int index = j;
                for(int t = index; t < waitingLine.length; t++)
                {
                    if(waitingLine[t] != null)
                    {
                        Client temp = waitingLine[t];
                        waitingLine[t] = null;
                        waitingLine[j] = temp;

                    }
                }
            }
        }
    }
    
    
    
}


}