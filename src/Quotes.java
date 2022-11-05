import java.sql.*;
import java.util.Scanner;

public class Quotes
{
    public static void main(String[] args)
    {
        int choice;
        String name,quo;

        Scanner sc=new Scanner(System.in);
        while (true)
        {
            System.out.println("Select an option");
            System.out.println("1. Add author");
            System.out.println("2. view all authors");
            System.out.println("3. search a author");
            System.out.println("4. update author");
            System.out.println("5. Delete author");
            System.out.println("6.Exit");

            choice=sc.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("Enter the author name");
                    name=sc.next();
                    System.out.println("Enter the quotes");
                    quo=sc.next();
                    System.out.println("Data inserted successfully");
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/quotedb","root","");
                        String sql="INSERT INTO `quotes`(`quote`, `author`) VALUES (?,?)";
                        PreparedStatement stmt=con.prepareStatement(sql);
                        stmt.setString(1,quo);
                        stmt.setString(2,name);
                        stmt.executeUpdate();
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);}


                    break;
                case 2:
                    System.out.println("view all authors selected");
                    try{

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/quotedb","root","");
                        String sql="SELECT * FROM `quotes`";
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql);
                        while(rs.next()){
                            String getQuo=rs.getString("quote");
                            String getName=rs.getString("author");
                            System.out.println("Quotes       : "+getQuo);
                            System.out.println("Author Name  : "+getName+"\n");

                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);}
                    break;
                case 3:
                    System.out.println("search a author selected");
                    System.out.println("Enter the author name for searching");
                    String au=sc.next();
                    try{

                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/quotedb","root","");
                        String sql="SELECT `quote`, `author` FROM `quotes` WHERE `author`='"+au+"'";
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery(sql);
                        while(rs.next()){
                            String getQuo=rs.getString("quote");
                            String getName=rs.getString("author");
                            System.out.println("Quotes       : "+getQuo);
                            System.out.println("Author Name  : "+getName+"\n");

                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e);}

                    break;
                case 4:
                    System.out.println("update author selected");
                    System.out.println("Enter the author name");
                    String aut=sc.next();
                    System.out.println("Enter the quotes to be updated");
                    quo=sc.next();
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quotedb", "root", "");
                        String sql = "UPDATE `quotes` SET `quote`='"+quo+"' WHERE `author`='"+aut+"'";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        System.out.println("updated successfully");
                    }
                    catch (Exception e){
                        System.out.println(e);}

                    break;
                case 5:
                    System.out.println("Enter the author for deleting data");
                    String auth=sc.next();
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quotedb", "root", "");
                        String sql = "DELETE FROM `quotes` WHERE `author`='"+auth+"'";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(sql);
                        System.out.println("Deleted successfully");
                    }
                    catch (Exception e){
                        System.out.println(e);}

                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Enter correct choice");
            }

        }
    }
}
