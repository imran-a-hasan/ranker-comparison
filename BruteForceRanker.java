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

public class BruteForceRanker
{
    private ExcelReader reader;
    private ArrayList<ArrayList<Integer>> first;
    private ArrayList<ArrayList<Integer>> second;
    private ArrayList<ArrayList<Integer>> third;
    private ArrayList data;
    private int firstMin;
    private int secondMin;
    private int thirdMin;
    private ArrayList<Integer> firstArr;
    private ArrayList<Integer> secondArr;
    private ArrayList<Integer> thirdArr;
    
    public BruteForceRanker (ArrayList<ArrayList<Integer>> one, 
                            ArrayList<ArrayList<Integer>> two, 
                            ArrayList<ArrayList<Integer>> three) throws Exception
    {
        first = one;
        second = two;
        third = three;
        firstMin = 1000;
        secondMin = 1000;
        thirdMin = 1000;
        reader = new ExcelReader();
        data = reader.makeList("totalwindiff.xls");
        firstArr = new ArrayList<Integer>();
        secondArr = new ArrayList<Integer>();
        thirdArr = new ArrayList<Integer>();
    }    
    
    public void start() throws Exception
    {
        int contradictions = -1;
        for (int i = 0; i < first.size(); i++)
        {
           ArrayList<Integer> temp = first.get(i);
           contradictions = numContradictions(temp);
           if (contradictions < firstMin)
           {
               firstMin = contradictions;
               setFirstMin(temp);
           }
        }       
        for (int i = 0; i < second.size(); i++)
        {
           ArrayList<Integer> temp = second.get(i); 
           contradictions = numContradictions(temp);
           if (contradictions < secondMin)
           {
               secondMin = contradictions;
               setSecondMin(temp);
           }
        }        
        for (int i = 0; i < third.size(); i++)
        {
           ArrayList<Integer> temp = third.get(i);
           contradictions = numContradictions(temp);
           if (contradictions < thirdMin)
           {
               thirdMin = contradictions;
               setThirdMin(temp);
           }
        }
        ArrayList<Integer> r2 = addAll();
        contradictionPrint(r2);
        System.out.println("\nContradictions: " + (firstMin + "" + secondMin + "" + thirdMin));
    }
    
    public void contradictionPrint (ArrayList<Integer> nums)
    {
      for (int i = 0; i < nums.size(); i++)
          for (int j = i+1; j < nums.size(); j++)
              contradiction2(nums.get(i), nums.get(j));
    }
    
    public void contradiction2 (int a, int b)
    {
       ArrayList row = (ArrayList)(data.get(a));
       if (((HSSFCell)(row.get(b))).getNumericCellValue() < 0)
           System.out.println("C: " + a + ", " + b);
    }
    
    public int numContradictions (ArrayList<Integer> nums)
    {
      int count = 0;
      for (int i = 0; i < nums.size(); i++)
      {
          for (int j = i+1; j < nums.size(); j++)
          {
              if (contradiction(nums.get(i), nums.get(j)))
                  count++;
          }
      }
      return count;
    }
    
    public boolean contradiction (int a, int b)
    {
       ArrayList row = (ArrayList)(data.get(a));
       if (((HSSFCell)(row.get(b))).getNumericCellValue() < 0)
           return true;
       return false;
    }
    
    private void setFirstMin (ArrayList<Integer> nums)
    {
        firstArr = new ArrayList<Integer>();
        for (int i = 0; i < nums.size(); i++)
            firstArr.add(nums.get(i));
    }
    
    private void setSecondMin (ArrayList<Integer> nums)
    {
        secondArr = new ArrayList<Integer>();
        for (int i = 0; i < nums.size(); i++)
            secondArr.add(nums.get(i));
    }
    
    private void setThirdMin (ArrayList<Integer> nums)
    {
        thirdArr = new ArrayList<Integer>();
        for (int i = 0; i < nums.size(); i++)
            thirdArr.add(nums.get(i));
    }   
    
    public ArrayList<Integer> addAll() throws Exception
    {
        ArrayList names = reader.makeList("wl%.xls");
        ArrayList<Integer> ranking = new ArrayList<Integer>();
        for (int i = 0; i < firstArr.size(); i++)
            ranking.add(firstArr.get(i));
        for (int i = 0; i < secondArr.size(); i++)
            ranking.add(secondArr.get(i));
        for (int i = 0; i < thirdArr.size(); i++)
            ranking.add(thirdArr.get(i));   
        for (int i = 0; i < ranking.size(); i++)
        {
            ArrayList temp = (ArrayList)(names.get(ranking.get(i)-1));
            String name = ((HSSFCell)(temp.get(0))).getStringCellValue();
            System.out.printf("%-2d %30s\n", ranking.get(i), name);
        }
        return ranking;
    }
}
