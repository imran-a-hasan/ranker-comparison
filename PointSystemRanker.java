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



public class PointSystemRanker
{
    private static ExcelReader reader;
    private static ArrayList data;
    private static ArrayList<Proposal> ranking;
    
    public static void main (String [] args) throws Exception
    {
       reader = new ExcelReader();
       data = reader.makeList("wl%.xls");
       ranking = new ArrayList<Proposal>();       
       PointSystemRanker start = new PointSystemRanker();
       start.calculatePoints();
       start.sortList();
       start.printList();

      start.checkForContradictions();
    }
    
    public void calculatePoints()
    {
       for (int i = 0; i < 20; i++)
       {
           ArrayList temp = (ArrayList)(data.get(i));
           double average = 0;
           for (int j = 1; j < temp.size(); j++)
           {
               if (j != i+1)
               {
                   if (((HSSFCell)(temp.get(j))).getNumericCellValue() > 0.5)
                       average += 3;
                   else if (((HSSFCell)(temp.get(j))).getNumericCellValue() == 0.5)
                       average += 1;
               }
           }
           Proposal p = new Proposal (((HSSFCell)(temp.get(0))).getStringCellValue(), average);
           ranking.add(p);
       }
    }
    
    public void sortList()
    {    
        Collections.sort(ranking, new CustomComparator());
    }
    
    public void printList()
    {
         for (int i = 0; i < ranking.size(); i++)
            System.out.println(ranking.get(i));
    }
    
    public void checkForContradictions()
    {
        System.out.println("\n\n");
        int counter = 0;
        Spreadsheet differentials = new Spreadsheet(data);
        for (int i = 0; i < ranking.size(); i++)
        {
            for (int j = i+1; j < ranking.size(); j++)
            {
                Proposal first = ranking.get(i);
                Proposal second = ranking.get(j);
                if (differentials.compare(first, second, false) < 50)
                    counter++;
            }
        }
        System.out.println(counter);
    }
}
