/**
 * Imran Hasan
 * Monta Vista High School
 * 408-834-5995
 * 
 * This program finds a Condorcet winner or loser within a data set. A Condorcet winner is defined as a member
 * of the data set that "beats" every other member, while a loser "loses" to every other member.
 * 
 * This code runs through the data set twice, once for determining winners and once for determining losers, and
 * compares each member to every other member. If one loses to another, it cannot be a Condorcet winner. 
 * Conversely, if one beats another, it cannot be a Condorcet loser.
 * 
 */

import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.IOException;
import java.util.ArrayList; // import necessary objects


public class CondorcetFinder
{
    private static ExcelReader reader; // reads Excel file
    private static ArrayList data; // holds Excel file
    private static ArrayList<Integer> winners; // holds list of Condorcet winners
    private static ArrayList<Integer> losers; // holds list of Condorcet losers
    private final int SIZE = 20;
    
    public static void main (String [] args) throws Exception
    {
       reader = new ExcelReader();
       data = reader.makeList("w%.xls"); // converts Excel file of each data member's win %s to ArrayList
       winners = new ArrayList<Integer>();
       losers = new ArrayList<Integer>();
       CondorcetFinder cf = new CondorcetFinder();
       cf.findWinner(winners.size()); // find Condorcet winners
       cf.findLoser(losers.size()); // find Condorcet losers
       cf.output();
    }
    
    public void findWinner(int size)
    {   
        int j = 100; // used to quit out of loop
        for (int i = 0; i < SIZE; i++)
        {
            ArrayList temp = (ArrayList)(data.get(i));
            for (j = 1; j < SIZE + 1; j++)
            {
                if (j != i + 1)
                    if (((HSSFCell)(temp.get(j))).getNumericCellValue() < 0.5) // if it loses to something
                        j = SIZE + 2; // quits loop
            }
            if (j <= SIZE + 1) // if the loop is never quit
                winners.add(new Integer(i)); // winner is found
        }
    }
    
    public void findLoser(int size)
    {
        int j = 100; // used to quit out of loop
        for (int i = 0; i < SIZE; i++)
        {
            ArrayList temp = (ArrayList)(data.get(i));
            for (j = 1; j < SIZE + 1; j++)
            {
                if (j != i + 1)
                    if (((HSSFCell)(temp.get(j))).getNumericCellValue() > 0.5) // if it beats something
                        j = SIZE + 2; // quits loop
            }
            if (j <= SIZE + 1) // if the loop is never quit
                losers.add(new Integer(i)); // loser is found
        }
    }
    
    public void output() // print winners and losers
    {
        System.out.println("Winners:\n");
        for (int i = 0; i < winners.size(); i++)
            System.out.println(winners.get(i) + 1);
        if (winners.size() == 0)
            System.out.println("None");
        System.out.println("\n\nLosers:\n");
        for (int i = 0; i < losers.size(); i++)
            System.out.println(losers.get(i) + 1);
        if (losers.size() == 0)
            System.out.println("None");
    }
}   