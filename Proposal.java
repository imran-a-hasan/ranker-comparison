public class Proposal
{
    private String name;
    private double average;
    private int index;
    
    public Proposal (String n, double av)
    {
        name = n;
        average = av;
    }
    
    public Proposal(int num)
    {
        index = num;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getAverage()
    {
        return average;
    }
    
    public String toString()
    {
        return String.format("%-60s %4f", name.trim(), average);
    }
}
