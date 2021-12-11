package auxiliary;

import responses.Responses;
import java.util.*;

public class FrontEnd {

    public void start(){
        loop : while(true){
            System.out.println(Responses.MAIN_MENU);
            int answer = getInt();
            switch (answer){
                case 0:{
                    break loop;
                }
            }
        }
    }
    public int getInt(){
        System.out.print("Input: ");
        return new Scanner(System.in).nextInt();
    }
    public String getStr(){
        System.out.print("Input: ");
        return new Scanner(System.in).next();
    }
    public double getD(){
        System.out.print("Input: ");
        return new Scanner(System.in).nextDouble();
    }
}
