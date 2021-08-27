/*
Adam Wiley
Sabhee Rehman
Ahad Samee
4/14/2021
We designed a Banking application. In this app, you can create and access accounts, create a balance and withdraw/deposit money, you are assigned an ID to access your accounts. You are able to change your information as you feel necessary.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Main {
  public static Scanner scan = new Scanner(System.in);
  public static int ID = 10000;
  public static ArrayList<String> accounts = new ArrayList<String>();
  public static boolean checkCorrect = false;

  // public static boolean checkAnswered = false;
  public static void main(String[] args) throws IOException {
    // System.out.println(intro()); //this was to test the intro function
    System.out.print("\033[H\033[2J");
    System.out.flush();
    /*
    String[] accountInfo = { "test", "test", "test", "test", "test", "100", "test" };
    accounts.add(accountInfo);
    */
    ID++;
     BufferedReader bf = new BufferedReader(new FileReader("Account.csv"));
      String line = null;
      while ((line = bf.readLine()) != null) {
      accounts.add(line);
      ID++;
      }    
      bf.close();
    boolean run = true;
    while (run == true) {
      
     
       route(intro());
      
      /* this was to test whether the account were being added to the Arraylist
      * for(String test : accounts ){ 
       * System.out.println(test);  }
      */
      }
  }

  public static String intro()throws IOException{
    boolean answered = false;
    // pre-conditional loop to ask the user for their original input
    
    
    while (answered == false) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("What would you like to do today. Create/Access/Shut Down");
      String answer = scan.nextLine();
      answer = answer.toLowerCase();
      // System.out.println(answer); //this is to test if the code was working
      // correctly
      if (answer.equals("create")) {
        // Ahad
        answered = true;
        return "create";
      } else if (answer.equals("access")) {
        // Adam and Sabhee
        answered = true;
        return "access";
      } else if (answer.equals("shut down")){
        changeCSV(accounts.size());
        System.exit(0);
      }else {
        answered = false;
        System.out.println("Please enter a valid answer");
      }
    }
    return "try again";
  }
  public static void changeCSV(int size)throws IOException{
    FileWriter cs = new FileWriter("Account.csv",false);
      for(String split : Main.accounts){
        String[] arrayAdd = split.split(",");
        for (int i = 0; i < arrayAdd.length; i++) {
          if (i != (arrayAdd.length - 1)) {
            cs.append(arrayAdd[i] + ",");
          } else {
              cs.append(arrayAdd[i] + "\n");
          }

        }
      }
    cs.flush();
    cs.close();
  }
  

  public static void route(String location) throws IOException{
    // Account creation questions
    if (location == "create") {
      System.out.println("\nPlease enter your first name");
      String firstName = scan.nextLine();
      System.out.println("\nPlease enter your last name");
      String lastName = scan.nextLine();
      System.out.println("\nPlease enter your address");
      String address = scan.nextLine();
      boolean valid = false;
          String phoneNumber = "0000000000";
          while(valid == false){
            System.out.println("\nPlease enter your phone number");
            newNumber = scan.nextLine();
            valid = Main.verifyNumber(newNumber);
      } 
      System.out.println("\nPlease enter your birthday mm/day/year");
      String bDay = scan.nextLine();
      System.out.println("\nPlease enter your starting balance");
      String balance = scan.nextLine();
      double balanceDouble = Double.parseDouble(balance);
      System.out.println("Your ID number is " + ID);
      System.out.println("Please enter a secure password for your account");
      String password = scan.nextLine();
      String[] accountInfo = { firstName, lastName, address, phoneNumber, bDay, balance, password };
      Account add = new Account(ID,firstName,lastName,address,phoneNumber,bDay,balanceDouble,password,false);
      accounts.add(add.changeArray());
      ID++;

      // Found the two lines of code below on a replit page, clears the console
      System.out.print("\033[H\033[2J");
      System.out.flush();

    }
    // Log in and Access existing account
    else if (location == "access") {

      checkCorrect = false;
      System.out.println("What is your account ID");
      int input = scan.nextInt();
      String space = scan.nextLine();
      System.out.println("\nPlease enter the password for this account");
      String checkPassword = scan.nextLine();
      // System.out.println(checkPassword);
      int newID = Math.abs(input - 10000);
      newID = newID - 1;
      String needSplit = accounts.get(newID);
      String[] array = needSplit.split(",");
      //String[] accessArray = accounts.get(newID);
      double setBalance = Double.parseDouble(array[5]);
      Account accountAccess = new Account(input, array[0], array[1], array[2], array[3],
          array[4], setBalance, array[6],true);
      while (checkCorrect == false) {
        System.out.println(accountAccess.toString(checkPassword));
        if (checkCorrect == false) {
          System.out.println("\nPlease enter the password for this account");
          checkPassword = scan.nextLine();
        }
      }
      boolean doneChanges = false;
      while (doneChanges == false) {
        // User chooses what they want to change
        // space = scan.nextLine();
        System.out.println(accountAccess.toString());
        System.out
            .println("What above would you like to change?\nName/Address/Phone/Birthday/Balance/Password/Log out");
        // space = scan.nextLine();
        String answer = scan.nextLine();
        answer = answer.toLowerCase();
        // System.out.println(answer);
        if (answer.equals("name")) {
          boolean nameAnswered = false;
          while (nameAnswered == false) {
            System.out.println("First or last name?");
            answer = scan.nextLine();
            answer = answer.toLowerCase();
            if (answer.equals("first")) {
              System.out.println("What would you like to change it to?");
              String newName = scan.nextLine();
              accountAccess.setFirst(newName);
              nameAnswered = true;
            } else if (answer.equals("last")) {
              System.out.println("What would you like to change it to?");
              String newName = scan.nextLine();
              accountAccess.setLast(newName);
              nameAnswered = true;
            } else {
              System.out.println("Please enter a valid answer");
            }
          }
        } else if (answer.equals("address")) {
          System.out.println("What is the new address?");
          String newAddress = scan.nextLine();
          accountAccess.setAddress(newAddress);

        } else if (answer.equals("phone")) {
          boolean valid = false;
          String newNumber = "0000000000";
          while(valid == false){
            System.out.println("What is the new phone number?");
            newNumber = scan.nextLine();
            valid = Main.verifyNumber(newNumber);
          }
          accountAccess.setPhoneNumber(newNumber);


        } else if (answer.equals("birthday")) {
          System.out.println("What is the correct birthday?");
          String newBday = scan.nextLine();
          accountAccess.setBday(newBday);
        } else if (answer.equals("balance")) {
          boolean balanceAnswer = false;
          while (balanceAnswer == false) {
            System.out.println("Would you like to deposit or withdraw?");
            String newBalanceAnswer = scan.nextLine();
            newBalanceAnswer = newBalanceAnswer.toLowerCase();
            if (newBalanceAnswer.equals("deposit")) {
              System.out.println("How much do you want to deposit?");
              double newDeposit = scan.nextDouble();
              space = scan.nextLine();
              accountAccess.deposit(newDeposit);
              balanceAnswer = true;
            } else if (newBalanceAnswer.equals("withdraw")) {
              System.out.println("How much do you want to withdraw?");
              double newWithdraw = scan.nextDouble();
              space = scan.nextLine();
              accountAccess.withdraw(newWithdraw);
              balanceAnswer = true;
            } else {
              System.out.println("Please write 'deposit' or 'withdraw'");

            }
          }
        } else if (answer.equals("password")) {
          boolean passCheckCorrect = false;
          while (passCheckCorrect == false) {
            System.out.println("What would you like to change the password to?");
            String newPass = scan.nextLine();
            System.out.println("Please verify the new password");
            String checkPass = scan.nextLine();
            if (newPass.equals(checkPass)) {
              accountAccess.setPassword(newPass);
              passCheckCorrect = true;
            } else {
              System.out.println("The passwords do not match");
            }
          }

        } else if (answer.equals("log out")) {
          doneChanges = true;
        } else {
          System.out.println("Please enter a valid answer");
        }
        if (doneChanges == false) {
          System.out.println("Are there any more changes?Yes/No");
          answer = scan.nextLine();
          if (answer.equals("yes")) {
            doneChanges = false;
          } else if (answer.equals("no")) {
            doneChanges = true;
            accounts.set(newID,accountAccess.changeArray());
          }
        }
      }
    }

  }
  public static boolean verifyNumber(String number){
      if(number.length() == 10){
          return true;
        }else{
      return false;
      }
  }
}

class Account {
  // declare account variables
  private int ID;
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String birthday;
  private double balance;
  private String password;
  


  Account(int ID, String firstName, String lastName, String address, String phoneNumber, String bDay, double balance,String password, boolean created) throws IOException{

    this.ID = ID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.birthday = bDay;
    this.balance = balance;
    this.password = password;
    /*
    if(created == false){
    FileWriter fw = new FileWriter("Changes.csv",true);
    String appendString = "\n"+ this.firstName + "," + this.lastName + "," + this.address + "," + this.phoneNumber + "," + this.birthday + "," + (this.balance + "") + "," + this.password;
    fw.append(appendString);
    fw.close();
    }*/
  }

  public String toString(String inPass) {
    if (this.password.equals(inPass)) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
      String statement = "ID: " + ID + "\nName: " + firstName + " " + lastName + "\nAddress: " + address
          + "\nPhone number: " + phoneNumber + "\nBirthday: " + birthday + "\nBalance: $" + balance + "\n";
      Main.checkCorrect = true;
      return statement;

    } else {
      Main.checkCorrect = false;
      return "That password is incorrect";

    }
  }

  public String toString() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    String statement = "ID: " + ID + "\nName: " + firstName + " " + lastName + "\nAddress: " + address
        + "\nPhone number: " + phoneNumber + "\nBirthday: " + birthday + "\nBalance: $" + balance + "\n";
    Main.checkCorrect = true;
    return statement;
  }

  // Set methods
  public void setFirst(String newName) {
    this.firstName = newName;
  }

  public void setLast(String newLast) {
    this.lastName = newLast;
  }

  public void setAddress(String newAddress) {
    this.address = newAddress;
  }

  public void setPhoneNumber(String newPhoneNumber) {
    this.phoneNumber = newPhoneNumber;
  }

  public void setBday(String newBday) {
    this.birthday = newBday;
  }

  public void deposit(double amount) {
    this.balance += amount;
  }

  public void withdraw(double amount) {
    if ((this.balance - amount) >= -100) {
      this.balance -= amount;
    } else {
      System.out.println("*Insufficent funds*");
    }
  }

  public void setPassword(String newPassword) {
    this.password = newPassword;
  }
  public String changeArray(){
    String appendString = this.firstName + "," + this.lastName + "," + this.address + "," + this.phoneNumber + "," + this.birthday + "," + (this.balance + "") + "," + this.password;
    return appendString;
  }
  
   /*
    FileWriter fw = new FileWriter("Account.csv");
      for(String split : Main.accounts){
        String[] arrayAdd = split.split(",");
        for (int i = 0; i < arrayAdd.length; i++) {
          if (i != (arrayAdd.length - 1)) {
            fw.append(arrayAdd[i] + ",");
          } else {
            fw.append(arrayAdd[i] + "\n");
        }
        }
      }
    fw.flush();
    fw.close();
    */
  
}