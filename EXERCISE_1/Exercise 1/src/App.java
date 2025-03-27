import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

    public class App {
        public static void main(String[] args) throws FileNotFoundException {
            Scanner loginScanner = new Scanner(System.in);

            System.out.print("Enter your username: ");

                String username_from_input = loginScanner.nextLine();

                System.out.print("Enter your password: ");
                
                String password_from_input = loginScanner.nextLine();

                System.out.println("your username is: " + username_from_input);
                System.out.println("your password is: " + password_from_input);


                File myfile = new File( "accounts.txt");

                User me = new User(username_from_input, password_from_input);


                Scanner fileScanner = new Scanner(myfile);

                while(fileScanner.hasNextLine()) {

                    String filedata = fileScanner.nextLine();
                    
                    String[] data = filedata.split(",");

                    String username_from_file = filedata.split(",")[0];
                    String password_from_file = filedata.split(",")[1];

                    if(username_from_input.equals(username_from_file) && password_from_input.equals(password_from_file)){
                    System.out.println("Login Sucessfully, Hello " + me.getUsername());
                    break;
                }else{
                    System.out.println("Login Failed");
                }
                    
                fileScanner.close();

                }
            }

        }
    


