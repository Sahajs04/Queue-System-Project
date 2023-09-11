public class ReturningItems extends Request
{
    // Declaring a variable called itemsToReturn that is an array of Strings.
    private String[] itemsToReturn;

    ///////////////////////////////////
    /**
     * This function returns the itemsToReturn array
     * 
     * @return The itemsToReturn array is being returned.
     */
    public String[] getItemsToReturn()
    {
        return itemsToReturn;
    }

    /**
     * This function sets the items to return
     * 
     * @param itemsToReturn The list of fields to return.
     */
    public void setItemsToReturn(String[] itemsToReturn)
    {
        this.itemsToReturn = itemsToReturn;
    }
////////////////////////////////////////////////////////

// This is a constructor.
public ReturningItems (String description, int priority, int difficulty, String[] itemsToReturn)
{
    setDescription(description);

    setPriority(priority);

    setDifficulty(difficulty);

    this.itemsToReturn = itemsToReturn;

    setFactor(3);

    setStatus(Status.NEW);

    setStartTime(0);

    setEndTime(0);

    setCompletionLevel(0);


}

///////////////////////////////////////////////////////////

/**
 * This function calculates the processing time for a return request.
 * 
 * @return The processing time of the return request.
 */
public int calculateProcessingTime()
{
    int processingTime = getDifficulty() * getFactor() * itemsToReturn.length;
    return processingTime;
}


}