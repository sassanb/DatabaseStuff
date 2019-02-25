package jdbcdemo;

import java.sql.*;


public class driverMain{
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/";

   //  Database credentials
   static final String USER = "student";
   static final String PASS = "password";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      System.out.println("Creating database...");
      stmt = conn.createStatement();
      
      //DROPS BOOKS ASSUMING IT EXISTS, FOR TESTING ONLY
      stmt.execute("DROP DATABASE IF EXISTS books");
      
      String sql = "CREATE DATABASE IF NOT EXISTS Books";
      stmt.executeUpdate(sql);
      System.out.println("Database created successfully...");
      
      //CREATES CONNECTION TO "books"
      Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books" , "student" , "password");
      
      //CREATES STATMENT TO "books" DATA BASE
      Statement myStmt = myConn.createStatement();
      
      //PASSES CONNECTION AND STATMENT TO tableCreator
      //@myStmt
      //@myConn
      tableCreator tc= new tableCreator(myStmt,myConn);
      
      //CREATES TABLE
      tc.createTable();
      tc.insertValues();
      
      //Select all authors alphabeticly
      
      System.out.println("");
      
      System.out.println("Here are all the authors");
      System.out.println("Listed: LASTNAME, FIRSTNAMT, AUTHOR ID");
      ResultSet myRs = stmt.executeQuery("SELECT * FROM books.authors ORDER BY last ASC");
      while(myRs.next()){
			System.out.println(myRs.getString("last") + " , " +  myRs.getString("first") + " , " + myRs.getString("authorID"));
		}
      System.out.println("");
      //Select all publishers
      System.out.println("Here are all the Publishers");
      System.out.println("Listed: PUBLISHER ID , PUBLISHER NAME");
      myRs = myStmt.executeQuery("SELECT * FROM books.publishers");
      while(myRs.next()){
			System.out.println(myRs.getString("publisherID") + " , " +  myRs.getString("publisherName"));
		}
      System.out.println("");
      System.out.println("Here are all the Publishers and all the books they made");
      System.out.println("Listed: ISBN , TITLE, COPYRIGHT YEAR");
     for(int i =1; i<15; i++){
      myRs = myStmt.executeQuery(" SELECT isbn, title, copyright FROM books.titles WHERE publisherID ="+i+"");

      while(myRs.next()){
			System.out.println(myRs.getString("isbn") + " , " +  myRs.getString("title") + " , " +  myRs.getString("copyright"));
		}
      
     }
     
     System.out.println("");
     System.out.println("Author Added");
      myStmt.execute("INSERT INTO books.authors (first, last) VALUES ('Nicholas', 'Bettencourt')");
      myRs = myStmt.executeQuery("SELECT * FROM books.authors WHERE authorID=16");
      while(myRs.next()){
			System.out.println(myRs.getString("last") + " , " +  myRs.getString("first") + " , " + myRs.getString("authorID"));
		}
      
      System.out.println("");
      System.out.println("Author Moddified");
      myStmt.execute("UPDATE books.authors SET first = 'Nick' WHERE authorID=16");
      myRs = myStmt.executeQuery("SELECT * FROM books.authors WHERE authorID=16");
      while(myRs.next()){
			System.out.println(myRs.getString("last") + " , " +  myRs.getString("first") + " , " + myRs.getString("authorID"));
		}
      
      System.out.println("");
      System.out.println("Title Added");
       myStmt.execute("INSERT INTO books.titles (isbn,title,editionNumber,price) VALUES ('1010101010', 'Almost Done','17','3.50')");
       myRs = myStmt.executeQuery("SELECT * FROM books.titles WHERE isbn=1010101010");
       while(myRs.next()){
 			System.out.println(myRs.getString("isbn") + " , " +  myRs.getString("title") + " , " + myRs.getString("editionNumber"));
 		}
       
       System.out.println("");
       System.out.println("Inserted Publisher");
       String insertPublisher = "INSERT INTO Books.publishers (publisherID,publisherName) VALUES ( 99, 'P1');";
       myStmt.execute(insertPublisher);
       myRs= myStmt.executeQuery("SELECT * FROM books.publishers WHERE publisherID='99'");
       while(myRs.next()){
			System.out.println(myRs.getString("publisherID") + " , " +  myRs.getString("publisherName"));
		}
       System.out.println("");
       System.out.println("Updated Publisher");
       String updatePublisher = "UPDATE Books.publishers SET publisherName='P99' WHERE publisherID=99;";
       myStmt.execute(updatePublisher);
       myRs= myStmt.executeQuery("SELECT * FROM books.publishers WHERE publisherID='99'");
       while(myRs.next()){
			System.out.println(myRs.getString("publisherID") + " , " +  myRs.getString("publisherName"));
		}
   /**   
      books[] book;
      bookBuilder bb= new bookBuilder();
      book = bb.buildBook();
    **/
      
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
  // System.out.println("Goodbye!");
}//end main
}//end JDBCExample
