public class InformationRequest extends Request
{
    // Declaring a variable called questions that is an array of strings.
    private String[] questions; 

    /////////////////////////////////////////////////////

    // This is a getter and setter for the questions array.
    public String[] getQuestions()
    {
        return questions; 
    }

    public void setQuestions(String[] questions)
    {
        this.questions = questions;

    }

    //////////////////////////////////////////////////////

   // A constructor that takes in the parameters of description, priority, difficulty, and questions.
   // It then sets the description, priority, difficulty, and questions to the parameters. It then sets
   // the factor to 1, the status to new, the start time to 0, the end time to 0, and the completion
   // level to 0.
    public InformationRequest (String description, int priority, int difficulty, String[] questions)
    {
        setDescription(description);

        setPriority(priority);

        setDifficulty(difficulty);

        setQuestions(questions);

        setFactor(1);

        setStatus(Status.NEW);

        setStartTime(0);

        setEndTime(0);

        setCompletionLevel(0);

    }

    //////////////////////////////////////////////////////////////
    /**
     * Calculates the total processing time of the request
     * 
     * @return The processing time 
     */
    public int calculateProcessingTime()
    {
        int processingTime = getDifficulty() * getFactor() * questions.length;
        return processingTime;
    }



}