import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;


public class Spreadsheet
{    
 private ArrayList data;    
 public Spreadsheet(ArrayList d)
 {
     data = d;
 }
 
 public int compare (Proposal first, Proposal second, boolean tof)
 {
     int ret = 0;
     int firstIndex = indexOf(first.getName());
     int secondIndex = indexOf(second.getName());
     ArrayList row = (ArrayList)(data.get(firstIndex));
    if (tof)
    {
        ret = (int)(((HSSFCell)(row.get(secondIndex+1))).getNumericCellValue());
        row = (ArrayList)(data.get(secondIndex));
        ret -= (int)(((HSSFCell)(row.get(firstIndex+1))).getNumericCellValue());
    }
    else
        ret = (int)((((HSSFCell)(row.get(secondIndex+1))).getNumericCellValue())*100);
     return ret;
 }
 
 public int indexOf (String name)
 {
    for (int i = 0; i < data.size(); i++)
    {
        ArrayList temp = (ArrayList)(data.get(i));
        if (((HSSFCell)(temp.get(0))).getStringCellValue().trim().equals(name.trim()))
            return i;
    }
    return -1; 
 }
}
