package auxiliary;

import responses.Responses;
import java.util.*;

public class FrontEnd {
    Message scanStr = new Message();
    Message scanInt = new Message();
    Message scanD = new Message();
    public void start(){
        loop : while(true){
            System.out.println(Responses.MAIN_MENU);
            int answer = scanInt.Message(new Scanner(System.in).nextInt());
        }
    }
}
