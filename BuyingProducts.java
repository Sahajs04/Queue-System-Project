public class BuyingProducts extends Request
{
    // Declaring a private variable called itemsToBuy that is an array of strings.
    private String[] itemsToBuy; 
////////////////////////////////////////////

	// This is a getter and setter for the itemsToBuy variable.
    public String[] getItemsToBuy() {
		return this.itemsToBuy;
	}

	public void setItemsToBuy(String[] itemsToBuy) {
		this.itemsToBuy = itemsToBuy;
	}

//////////////////////////////////////////////////////

// This is a constructor for the BuyingProducts class. It is initializing the variables for the class.
public BuyingProducts (String description, int priority, int difficulty, String[] itemsToBuy)
{
    setDescription(description);

    setPriority(priority);

    setDifficulty(difficulty);

    this.itemsToBuy = itemsToBuy;

    setFactor(2); 

    setStatus(Status.NEW);

    setStartTime(0);

    setEndTime(0);

    setCompletionLevel(0);
}

/**
 * This function calculates the processing time of a buying products request.
 * 
 * @return The processing time of the buying products request.
 */
public int calculateProcessingTime()
{
    int processingTime = getDifficulty() * getFactor() * itemsToBuy.length; 
    return processingTime;

}




}