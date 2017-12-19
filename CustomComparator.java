import java.util.Comparator;

public class CustomComparator implements Comparator<Proposal>
{
    public int compare (Proposal a, Proposal b) 
    {
        return (int)(b.getAverage()/*100*/ - a.getAverage()/*100*/);
    } 
}
