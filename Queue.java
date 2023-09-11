public class Queue
{
    // Declaring the variables that will be used in the class.
    private String serverName;

    private int queueSize;

    private Client clientBeingServed;

    private Request requestInProgress;

    private int processingStartTime;

    private Client[] clientsHistory = new Client[0];

    private Client[] clientsInQueue;

    //////////////////////////////////////////////////
    // Getter and Setter methods
    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getQueueSize() {
        return this.queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public Client getClientBeingServed() {
        return this.clientBeingServed;
    }

    public void setClientBeingServed(Client clientBeingServed) {
        this.clientBeingServed = clientBeingServed;
    }

    public Request getRequestInProgress() {
        return this.requestInProgress;
    }

    public void setRequestInProgress(Request requestInProgress) {
        this.requestInProgress = requestInProgress;
    }

    public int getProcessingStartTime() {
        return this.processingStartTime;
    }

    public void setProcessingStartTime(int processingStartTime) {
        this.processingStartTime = processingStartTime;
    }

    public Client[] getClientsHistory() {
        return this.clientsHistory;
    }

    public void setClientsHistory(Client[] clientsHistory) {
        this.clientsHistory = clientsHistory;
    }

    public Client[] getClientsInQueue() {
        return this.clientsInQueue;
    }

    public void setClientsInQueue(Client[] clientsInQueue) {
        this.clientsInQueue = clientsInQueue;
    }

    ////////////////////////////////////////////////////////////////

    // A constructor that takes in a server name and a queue size and creates a new queue with the
    // given server name and queue size.
    public Queue (String serverName, int queueSize)
    {
        this.serverName = serverName;

        this.queueSize = queueSize;

        clientsInQueue = new Client[queueSize];

    }
    /////////////////////////////////////////////////////////////////

    //returns the queues information as a string
    public String toString()
    {
        return toString(true);

    }

    /**
     * This function is used to print out the queue as a string in the format of [Queue:1][  ]-----[  ][  ][  ][ 
     * ][  ][  ][  ][  ][  ][  ]
     * 
     * @param showID boolean
     * @return The return type is a string.
     */
    public String toString(boolean showID)
    {
        Queue[] system = QueueSystem.getQueues();
        String s = "";
        int num = 0;
        int id;
        String idd;
        int id1;
        String idd1;
        if(showID == false)
        {
            int num1 = 0; 
            String ss = "";
            String time = "";
            int total;
            int total1;
            String time1 = "";
            Queue[] system1 = QueueSystem.getQueues();
            
            
            for(int i = 0; i < QueueSystem.getQueues().length; i++)
            {
                if(serverName.equals(system1[i].getServerName()))
                {
                    num1 = i+1;
                }
            }
            
            //////////////////////////////////////////////////////
            //////////////////////////////////////////////////////
             ss = "[Queue:" + num1 + "]";
             if(clientBeingServed == null)
             {
                ss+= "[  ]-----";
             }
             else{
            total = totalProcessingTimeNum(clientBeingServed) - clientBeingServed.getTimeServed();
    
             if(total < 10)
             {
            time = (0 + "" + total);
            }
            else
            {
                time = ("" + total);
            }
            ss+= "[" + time + "]-----";
        }
            for(int i = 0; i < clientsInQueue.length; i++)
            {
                if(clientsInQueue[i] == null)
                {
                    ss+= "[  ]";
                }
                else
                {
                total1 = totalProcessingTimeNum(clientsInQueue[i]);
                if(total1 < 10)
                {
               time1 = (0 + "" + total1);
               ss+= "[" + time1 + "]";
               }
               else 
               {
                time1 = ("" + total1);
                ss+= "[" + time1 + "]";
               }
            }
        }
            return ss;
        }
        //////////////////////////////////////////////////////////////////////
        else
        {

            for(int i = 0; i < QueueSystem.getQueues().length; i++)
            {
                if(serverName.equals(system[i].getServerName()))
                {
                    num = i+1;
                }
            }
            ////////////////////////////////////////////////////
             s = "[Queue:" + num + "]";
             if(clientBeingServed == null)
             {
                s+="[  ]-----";
             }
             else{
             id = clientBeingServed.getId();
             if(id < 10)
             {
                idd = (0 +"" + id);
             }
             else
             {
                idd = ("" + id);
             }
             s+= "[" + idd + "]" + "-----";
            }
             for(int i = 0; i < clientsInQueue.length; i++)
             {
                if(clientsInQueue[i] == null)
                {
                s+= "[  ]";
                continue;
                }
                else
                {
                id1 = clientsInQueue[i].getId();
                if(id1 < 10)
                {
                    idd1 = (0 + "" + id1);
                    s+= "[" + idd1 + "]";
                }
                else 
                {
                    s+= "[" + id1 + "]";
                }
            }

         }
             return s;
    }


 }

//fixxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
/**
 * This function calculates the total processing time of all the requests of a client
 * 
 * @param client The client object
 * @return The total processing time of all the requests for a client.
 */
public String totalProcessingTimeString(Client client)
{
    int total = 0;
    Request[] requests;
    if(client.getRequests() != null)
    {
     requests = client.getRequests();
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
/////////////////////////////////////////////////////////
//the above method but returns the totalProcessingTime as a int vs string
public int totalProcessingTimeNum(Client client)
{
    int total = 0;
    Request[] requests;
    if(client.getRequests() != null)
    {
     requests = client.getRequests();
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

////////////////////////////////////////////////////////////

}
