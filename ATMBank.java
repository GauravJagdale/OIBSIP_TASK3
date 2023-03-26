import java.util.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.sql.SQLOutput;

class bankaccounts{
    static  void register(){
        Scanner sc=new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter your full name :");
        ATMBank.name=sc.nextLine();
        System.out.println("Enter your username :");
        String user=sc.nextLine();
        System.out.println("Enter your password :");
        String password=sc.nextLine();
        System.out.println("Enter your Account number :");
        ATMBank.accountnumber=sc.nextLine();
        System.out.println("REGISTRATION IS DONE SUCCESSFULLY!");
        System.out.println("---------------------------");
        ATMBank.prompt();
        while(true){
            display(ATMBank.name);
            int choice=sc.nextInt();
            if(choice==1){
                login(user,password);
                break;
            }
            else {
                if(choice==2){
                    System.exit(0);
                }
                else{
                    System.out.println("Incorrect value! Enter again!");
                }
            }
        }
    }
    static void display(String name){}
    static void login(String user,String password){}
}
class transaction{
    static void withdraw(){
        Scanner sc=new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Enter amount to withdraw :");
        double withdrawcash=sc.nextDouble();
        if(withdrawcash<=ATMBank.balance){
            ATMBank.balance=ATMBank.balance-withdrawcash;
            ATMBank.history.add(Double.toString(withdrawcash));
            ATMBank.history.add("Withdraw");
            System.out.println("Amount Rs"+withdrawcash+"/-withdraw successfully");
            System.out.println("---------------------------");
        }
        else{
            System.out.println("Insufficient balance to withdraw the cash");
            System.out.println("---------------------------");
        }
        ATMBank.prompt();
    }
    static void deposit(){
        Scanner sc=new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit :");
        double depositcash=sc.nextDouble();
        ATMBank.updatebalance(depositcash);
        ATMBank.history.add(Double.toString(depositcash));
        ATMBank.history.add("Deposit");
        System.out.println("Amount Rs."+depositcash+"/- deposited successfully!");
        System.out.println("---------------------------");
        ATMBank.prompt();
    }
    static void transfer(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the name of receiving body:");
        String s=sc.nextLine();
        System.out.println("Enter the account number of the receiving body:");
        int num=sc.nextInt();
        System.out.println("Enter the amount to be transferred :");
        double transfercash=sc.nextDouble();
        if(transfercash<=ATMBank.balance){
            ATMBank.balance=ATMBank.balance-transfercash;
            ATMBank.history.add(Double.toString(transfercash));
            ATMBank.history.add("Transferred");
            System.out.println("Amount Rs."+transfercash+"/- transferred successfully");
            System.out.println("---------------------------");
        }
        else{
            System.out.println("Insufficient balance to transfer the cash");
            System.out.println("---------------------------");
        }
    }
}
class checking{
    static void checkbalance(){
        System.out.println("------------------");
        System.out.println("The available balance in the bank account :");
        ATMBank.showbalance();
        System.out.println("---------------------------");
        ATMBank.prompt();
    }
}
class thistory{
    static void transactionhistory(){
            System.out.println("---------------------");
            System.out.println("Transaction History :");
            int k=0;
            if(ATMBank.balance>0){
            for(int i=0;i<(ATMBank.history.size()/2);i++)
            {
                for(int j=0;j<2;j++)
                {
                    System.out.print(ATMBank.history.get(k)+" ");
                    k++;
                }
                System.out.println("---------------------");
            }
            }
            else {
                System.out.println("Your account is empty");
            }
            ATMBank.prompt();
    }
}
public class ATMBank {
    public static String name;
    public static double balance=0;
    public static String accountnumber;
    public static ArrayList<String> history=new ArrayList<String>();

    static void updatebalance(double dcash){
        balance=balance+dcash;
    }
    static void showbalance(){
        System.out.println(balance);
    }
    public static void homepage(){
        System.out.println("\033[H\033[2J");
        Scanner sc=new Scanner(System.in);
        System.out.println("WELCOME TO ATM INTERFACE OF BANK");
        System.out.println("--------------------------");
        System.out.println("Select option :");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.println("Enter choice");
        int choice =sc.nextInt();
        if (choice==1){
                bankaccounts.register();
        }
        else {
            if(choice==2){
                System.exit(0);
            }
            else{
                System.out.println("select a value only from the given options :");
                homepage();
            }
        }
    }
    static void prompt(){
        Scanner sc=new Scanner(System.in);
        System.out.println("WELCOME "+ATMBank.name+"! TO ATM SYSTEM");
        System.out.println("---------------------");
        System.out.println("Select option : ");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Enter your choice : ");
        int choice=sc.nextInt();
        switch (choice) {
            case 1:
                transaction.withdraw();
            case 2:
                transaction.deposit();
            case 3:
                transaction.transfer();
            case 4:
                checking.checkbalance();
            case 5:
                thistory.transactionhistory();
            case 6:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        homepage();
    }
}