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

public class CambridgeRank
{
    private static ExcelReader reader;
    private static ArrayList data;
    private static ArrayList<Proposal> ranking;
    
    public static void main (String [] args) throws Exception
    {
       reader = new ExcelReader();
       data = reader.makeList("windiff2.xls");
       ranking = new ArrayList<Proposal>();       
       CambridgeRank start = new CambridgeRank();
       start.calculateAverages();
       start.sortList();
       start.printList();
       start.checkForContradictions();
    }
    
    public void calculateAverages()
    {
       for (int i = 0; i < data.size(); i++)
       {
           ArrayList temp = (ArrayList)(data.get(i));
           double tempSum = 0;
           double counter = 0;
           double average = 0;
           for (int j = 1; j < temp.size(); j++)
           {
               if (j != i+1)
               {
                  tempSum += ((HSSFCell)(temp.get(j))).getNumericCellValue();
                  counter++;
               }
           }
           for (int k = 0; k < data.size(); k++)
           {
               ArrayList temp2 = (ArrayList)(data.get(k));
               if (k != i+1)
               {
                   tempSum -= ((HSSFCell)(temp2.get(i+1))).getNumericCellValue();
                   counter++;
               }
           }
           average = tempSum/counter;
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
        Spreadsheet differentials = new Spreadsheet(data);
        int counter = 0;
        for (int i = 0; i < ranking.size(); i++)
        {
            for (int j = i+1; j < ranking.size(); j++)
            {
                Proposal first = ranking.get(i);
                Proposal second = ranking.get(j);
                if (differentials.compare(first, second, true) < 0)
                   counter++;
            }
        }
        System.out.println(counter);
    }
}
