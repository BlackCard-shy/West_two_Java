import java.sql.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int flag=0;
        TurnJSON.turnData(flag);//运行程序，自动更新
        //System.out.println(response);response即为获取到的API
        System.out.println("Welcome.");
        flag=1;
        System.out.println("Please enter your choice:");
        System.out.println("1. Update   2. Inquiry");
        Scanner input = new Scanner(System.in);
        int choose=input.nextInt();
        if(choose==1) {
            TurnJSON.turnData(flag);//手动更新
        }
        else if(choose==2) {
            System.out.println("Enter the city you want to query:");
            Scanner input_1 = new Scanner(System.in);
            String City_name =input_1.next();
            TurnJSON.queryData(City_name);
        }

    }
}
