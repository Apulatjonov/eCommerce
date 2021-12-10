import auxiliary.AdminPanel;
import service.UserService;

public class Main {
    public static void main(String[] args){
        AdminPanel.run(new UserService());
    }
}