import java.util.Random;


public class RandTask
{

    public String Generate()
    {
        String[] verblist = {"fix", "repair","buff","clean","polish",
                "wax", "paint", "tidy", "declutter", "inspect"};
        String[] quallist = {"northern", "southern","manager's","large","small",
                "broken", "buffalo", "new", "replacement", "reticulated"};               
        String[] nlist = {"bat", "transmitter","water","gas","electrical",
                "storage", "box", "supply", "workmen's", "emergency"};              
        String[] mlist = {"flange", "shed","pipe","car","fence",
                "gate", "lock", "door", "window", "furnace"};        
        
        String answer="";
        Random rand = new Random();

        int  n = rand.nextInt(10);
        n= rand.nextInt(10);
        answer= answer+verblist[n]+" the ";
        
        n = rand.nextInt(10);
        if(n<4)
        {
            n= rand.nextInt(10);
            answer= answer+quallist[n]+" ";
        }
        
        n = rand.nextInt(10);
        if(n<4)
        {
            n= rand.nextInt(10);
            answer= answer+nlist[n]+" ";
        }        
        
        n= rand.nextInt(10);
        answer= answer+mlist[n];   
        n = rand.nextInt(10);
        if(n<4)     
        {
            answer=answer+"s";
        }
        
        return answer;
    }
    
    public static void main()
    {
        RandTask r = new RandTask();
        
        for(int i=0;i<10;i++)
        {
            System.out.println(r.Generate());
        }
    }
}
