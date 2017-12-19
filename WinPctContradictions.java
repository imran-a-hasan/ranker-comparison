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

public class WinPctContradictions
{
    private static ExcelReader reader;
    private static ArrayList data;
    private static ArrayList<Proposal> ranking;
    public static void main (String [] args) throws Exception
    {
       reader = new ExcelReader();
       data = reader.makeList("totalwin%.xls");
       ranking = new ArrayList<Proposal>();       
       WinPctContradictions start = new WinPctContradictions();
       start.makeProposals();
       start.sortList();
       start.printList();
       start.checkForContradictions();
    }
    
    public void makeProposals()
    {
       for (int i = 0; i < data.size(); i++)
       {
           ArrayList temp = (ArrayList)(data.get(i));
           double average = ((HSSFCell)(temp.get(1))).getNumericCellValue();
           Proposal p = new Proposal (((HSSFCell)(temp.get(0))).getStringCellValue(), average*100);
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
    
    public void checkForContradictions() throws Exception
    {
       System.out.println("\n\n");
        int counter = 0;
        ExcelReader er = new ExcelReader();
        Spreadsheet differentials = new Spreadsheet(er.makeList("wl%.xls"));
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
