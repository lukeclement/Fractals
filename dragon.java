import java.util.*;

public class dragon{
    public static List lists(int max){
        Scanner scan=new Scanner(System.in);
        //System.out.println("Number of lines?");
        int maxLine=max;
        System.out.println("Finding "+maxLine+" lines");
        List<Integer> lines=new ArrayList<>();
        lines.add(3);
        lines.add(1);
        for(int i=2;i<=maxLine;i++){
            //System.out.println("found "+i);
            double j=(double)i;
            if((j/2)==Math.floor(j/2)){
                if((i/2)%2==1){
        //            System.out.println(i+" IS RIGHT");
                    lines.add(1);
                }
                if((i/2)%2==0){
        //          System.out.println(i+" IS LEFT");
                    lines.add(0);
                }

            }
            else{
            //    System.out.println(i+" IS COMPUTE "+lines.get((i+1)/2));
                lines.add(lines.get((i+1)/2));
            }
        }
        for(int i=2;i<lines.size();i++){
            if(lines.get(i)==1){
              //  System.out.println((i-1)+" to "+i+"= Right");
            }
            if(lines.get(i)==0){
                //System.out.println((i-1)+" to "+i+"= Left");
            }
        }
        return lines;

    }
}
