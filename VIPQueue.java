public class VIPQueue extends Queue
{
   
   // A private variable that is used to determine if the queue is accepting any clients.
    private boolean acceptingAnyClients;
    //////////////////////////////////////////////////
    // A getter and setter method.
    public boolean isAcceptingAnyClients() {
        return this.acceptingAnyClients;
    }

    public void setAcceptingAnyClients(boolean acceptingAnyClients) {
        this.acceptingAnyClients = acceptingAnyClients;
    }

    /////////////////////////////////////////////


    // A constructor that takes in a server name, queue size, and a boolean value.
    public VIPQueue (String serverName, int queueSize, boolean acceptAnyClients)
    {
        super(serverName, queueSize);

        this.acceptingAnyClients = acceptAnyClients;


    }

    // A constructor that takes in a server name and a queue size.
    public VIPQueue (String serverName, int queueSize)
    {
        super(serverName, queueSize);

    }

///////////////////////////////////////////////////////


}