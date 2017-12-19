import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class SmithSetFinder
{
    private static ExcelReader reader;
    private static ArrayList data;
    private static ArrayList<Integer> winners;
    private static ArrayList<Integer> losers;
    
    public static void main (String [] args) throws Exception
    {
       reader = new ExcelReader();
       data = reader.makeList("windiff2.xls");
       winners = new ArrayList<Integer>();
       losers = new ArrayList<Integer>();
       SmithSetFinder ssf = new SmithSetFinder();
       ssf.makeSets();
       ssf.output();  
    }
    
    public void makeSets()
    {
        winners.add(new Integer(1));
        for (int i = 0; i < 20; i++)
        {
            ArrayList temp = (ArrayList)(data.get(i));
            for (int j = 1; j < 21; j++)
            {
                if (j!=i+1)
                {
                ArrayList oppTemp = (ArrayList)(data.get(j-1));
                double diff = ((HSSFCell)(temp.get(j))).getNumericCellValue();
                diff -= ((HSSFCell)(oppTemp.get(i+1))).getNumericCellValue();
                if (diff > 0)
                {
                   if (!losers.contains(new Integer(j))) 
                   {
                        losers.add(new Integer(j));
                        if (winners.contains(new Integer(j)))
                            winners.remove(new Integer(j));
                   }
                }
                else
                {
                    if (!winners.contains(new Integer(j)))
                    {
                        winners.add(new Integer(j));
                        if (losers.contains(new Integer(j)))
                            losers.remove(new Integer(j));
                    }
                }}
            }
        }      
    }
    
    public void output()
    {
        Collections.sort(winners);
        Collections.sort(losers);
        System.out.println("Winners:\n");
        for (int i = 0; i < winners.size(); i++)
            System.out.println(winners.get(i));
        System.out.println("\n\nLosers:\n");
        for (int i = 0; i < losers.size(); i++)
            System.out.println(losers.get(i));
    }
}
