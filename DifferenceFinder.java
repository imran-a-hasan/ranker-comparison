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
import java.io.PrintWriter;

public class DifferenceFinder
{
    private static ExcelReader reader;
    private static ArrayList wPct, lPct;
    private static ArrayList<Double> diffs;
    public static void main (String [] args) throws Exception
    {
       reader = new ExcelReader();
       wPct = reader.makeList("w%.xls");
       lPct = reader.makeList("l%.xls");
       diffs = new ArrayList<Double>();
       DifferenceFinder df = new DifferenceFinder();
       df.findDiffs();
    }
    
    public void findDiffs()
    {
       double tempNum = 0.0;
       for (int i = 0; i < 20; i++)
       {
           ArrayList temp = (ArrayList)(wPct.get(i));
           for (int j = 1; j < i+1; j++)
           {
              double first = ((HSSFCell)(temp.get(j))).getNumericCellValue();
              ArrayList temp2 = (ArrayList)(wPct.get(j-1));
              double second = ((HSSFCell)(temp2.get(i+1))).getNumericCellValue();
              if (first > second)
              {
                 ArrayList lTemp = (ArrayList)(lPct.get(j-1));
                  tempNum = ((HSSFCell)(lTemp.get(i+1))).getNumericCellValue();
                 diffs.add(new Double(first - tempNum)); 
              }
              else
              {
                 ArrayList lTemp = (ArrayList)(lPct.get(i));
                  tempNum = ((HSSFCell)(lTemp.get(j))).getNumericCellValue();
                 diffs.add(new Double(second - tempNum)); 
              }
           }
       }
       listOutput();
    }

    
    public void listOutput()
    {
         PrintWriter writer = OpenFile.openToWrite("diffs");
         double sum = 0.0;
         for (int i = 0; i < diffs.size(); i++)
         {
           sum += diffs.get(i);
           writer.println(diffs.get(i));
         }
         System.out.print("Average: " + sum/diffs.size()*100 + "%"); 
         writer.close();
    }    
}

