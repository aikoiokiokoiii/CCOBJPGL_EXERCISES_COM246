import java.util.Scanner;

import javax.xml.crypto.Data;

import java.io.File;

public class App {
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {

    //     System.out.println("username: ");

    //     Scanner loginScanner = new Scanner(System.in);

    //     String username_from_input = loginScanner.nextLine();

    //     System.out.println("password: ");

    //     String password_from_input = loginScanner.nextLine();

    //     User me = new User(username_from_input, password_from_input);

    //     File myFile = new File ("accounts.text");
    //     if (myFile.exists()) {
    //         System.out.println("File exists");  
    //     }
    //      Scanner fileScanner = new Scanner(myFile);

    //      Boolean accountexist = false;
    //     while (fileScanner.hasNextLine()) {
    //         String filedata = fileScanner.nextLine();

    //         String username_from_file = filedata.split(",")[0];
    //         String password_from_file = filedata.split(",")[1];
    //         if (username_from_input.equals(username_from_file) && password_from_input.equals(password_from_file)) {
    //             accountexist = true;
    //             break;
    //         }
    //      }

    //      if (accountexist){
    //         System.out.println("Logged in kana boss, Kamusta " + me.getUsername());
    //      } else {
    //         System.out.println("Walang ganyang account wag moko emehin");
    //     loginScanner.close();
    //      fileScanner.close();
    //  }


     DataScientist RD1 = new DataScientist("John", "",  0);
     Researcher RD2 = new Researcher("Jane", "", 0);

        System.out.println("\nThe name is " + RD1.name);
        System.out.println("Works as " + RD1.getWork());
        System.out.println("And he earns " + RD1.getSalary());

        System.out.println("\nThe name is " + RD2.name);
        System.out.println("Works as " + RD2.getWork());
        System.out.println("And he earns " + RD2.getSalary());

        Car MyCar = new Car();
        System.out.println("My car has " + MyCar.cylinders + "cylinders and " + MyCar.battery + "battery.");

 }
}