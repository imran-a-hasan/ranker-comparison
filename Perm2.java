import java.util.ArrayList;

public class Perm2
{
    private int size;
    private ArrayList<Integer> nums;
    private ArrayList<ArrayList<Integer>> numsList;
    
    public Perm2 (int s)
    {
        size = s;
        nums = new ArrayList<Integer>();
        numsList = new ArrayList<ArrayList<Integer>>();
        for (int z = 0; z < size; z++)
            nums.add(z);
    }
    
    public Perm2 (ArrayList<Integer> n)
    {        
        size = 0;
        nums = new ArrayList<Integer>();
        numsList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n.size(); i++)
        {
            size++;
            nums.add(n.get(i));
        }
    }
    
    
    public void start()
    {    
        do
        {
            add(nums);
            nums = next(nums);
        } while (nums != null);
    }
    
    public ArrayList<Integer> next(ArrayList<Integer> nums2)
    {                
        int i = -1;
        int a = -1;
        for (i = 0; i < size - 1; i++)
            if (nums2.get(i) < nums2.get(i+1))
                a = i;
        int b = -1;
        if(a != -1)
        {
            for (int j = a+1; j < size; j++)
            {
                if (nums2.get(j) > nums2.get(a))
                    b = j;
            }
            int temp = nums2.get(a);
            nums2.set(a, nums2.get(b));
            nums2.set(b, temp);
            int length = nums2.size() - a - 1;
            reverse(nums2, a);            
            return nums2;
        }
        return null;
    }
    
    public void reverse (ArrayList<Integer> nums2, int a)
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < size-a-1; i++)
        {
            temp.add(nums2.get(i+a+1));
        }
        for (int i = 0; i < temp.size()/2; i++)
        {
            int tempNum = temp.get(i);
            temp.set(i, temp.get(temp.size()-i-1));;
            temp.set(temp.size()-i-1, tempNum);
        }
        for (int i = a+1; i < size; i++)
        {
            nums2.set(i, temp.get(i-a-1));
        }
    }
    
    public void print(ArrayList<Integer> nums)
    {
        for (int i = 0; i < size; i++)
            System.out.print(nums.get(i) + " ");
        System.out.println();    
    }
    
    public void add(ArrayList<Integer> nums3)
    {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; nums3 != null && i < nums3.size(); i++)
        {
            temp.add(new Integer(nums3.get(i)));
        }
        numsList.add(temp);
    }
    
    public ArrayList<ArrayList<Integer>> getPerms()
    {
        return numsList;
    }
}
