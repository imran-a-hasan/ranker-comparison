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

public class RankFinder
{
    private static ArrayList<Integer> one;
    private static ArrayList<Integer> two;
    private static ArrayList<Integer> three;
    private static ArrayList<ArrayList<Integer>> first;
    private static ArrayList<ArrayList<Integer>> second;
    private static ArrayList<ArrayList<Integer>> third;
    
    public static void main (String [] args) throws Exception
    {
       one = new ArrayList<Integer>();
       two = new ArrayList<Integer>();
       three = new ArrayList<Integer>();
       first = new ArrayList<ArrayList<Integer>>();
       second = new ArrayList<ArrayList<Integer>>();
       third = new ArrayList<ArrayList<Integer>>();
       one.add(1);
       one.add(2);
       one.add(3);
       one.add(4);
       two.add(5);
       two.add(6);
       two.add(7);
       two.add(8);
       two.add(9);
       two.add(10);
       two.add(11);
       two.add(12);
       three.add(13);
       three.add(14);
       three.add(15);
       three.add(16);
       three.add(17);
       three.add(18);
       three.add(19);
       three.add(20);
       Perm2 top = new Perm2(one);
       Perm2 middle = new Perm2(two);
       Perm2 bottom = new Perm2(three);
       top.start();
       middle.start();
       bottom.start();
       first = top.getPerms();
       second = middle.getPerms();
       third = bottom.getPerms();
       BruteForceRanker bfr = new BruteForceRanker(first, second, third);
       bfr.start();
    }
    
    public static void printSets()
    {
       System.out.println("1:\n");
       for (int i = 0; i < one.size(); i++)
           System.out.println(one.get(i));
       System.out.println("\n2:\n");
       for (int i = 0; i < two.size(); i++)
           System.out.println(two.get(i));
       System.out.println("\n3:\n");
       for (int i = 0; i < three.size(); i++)
           System.out.println(three.get(i));
    }
}