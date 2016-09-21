import java.util.*;

public class dragon{
    public static List lists(int max){
        Scanner scan=new Scanner(System.in);
        int maxLine=max;
        System.out.println("Finding "+maxLine+" lines");
        List<Integer> lines=new ArrayList<>();
        lines.add(3);
        lines.add(1);
        for(int i=2;i<=maxLine;i++){
            double j=(double)i;
            if((j/2)==Math.floor(j/2)){
                if((i/2)%2==1){
                    lines.add(1);
                }
                if((i/2)%2==0){
                    lines.add(0);
                }

            }
            else{
                lines.add(lines.get((i+1)/2));
            }
        }
        return lines;

    }
}
