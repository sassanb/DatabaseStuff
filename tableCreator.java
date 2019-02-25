package jdbcdemo;

import java.sql.*;

import java.util.*;

public class tableCreator {
	Statement stMt;
	Connection coNN;
	public tableCreator(Statement stmt, Connection conn) {
		stMt=stmt;
		coNN=conn;
	}
	public void createTable(){
	     /* String sql1 = "CREATE TABLE AUTHOR " +
                  "(id INTEGER not NULL, " +
                  " first VARCHAR(255), " + 
                  " last VARCHAR(255), " + 
                  " age INTEGER, " + 
                  " PRIMARY KEY ( id ))"; */

		String sql1="CREATE TABLE IF NOT EXISTS authors "
                  + "(authorID INTEGER not NULL AUTO_INCREMENT, "
	    		  +	" first VARCHAR(255), "
                  + " last VARCHAR(255), "
	    		  + " PRIMARY KEY ( authorID ))";
	      
	      
	      String sql2="CREATE TABLE IF NOT EXISTS authorISBN "
                  + "(authorID INT, " 
	    		  + "isbn CHAR(10), "
	    		  + "FOREIGN KEY(isbn) REFERENCES titles(isbn))";
	      
	      
	      String sql3="CREATE TABLE IF NOT EXISTS titles "
	    		  + "(isbn CHAR(10), " 
	    		  + " title VARCHAR(500), " 
	    		  + " editionNumber INTEGER, " 
	    		  + " copyright CHAR(4), " 
	    		  + "publisherID INTEGER, " 
	    		  + "price FLOAT, " 
	    		  + " PRIMARY KEY ( isbn), "
	    		  + "FOREIGN KEY(publisherID) REFERENCES publishers(publisherID))";
	      
	      String sql4="CREATE TABLE IF NOT EXISTS publishers " 
	    		  + "(publisherID INTEGER, " 
	    		  + " publisherName char(100), "
	    		  + " PRIMARY KEY ( publisherID))";

                  
	      
	      try{
	      stMt.executeUpdate(sql1);
	      stMt.executeUpdate(sql4);
	      stMt.executeUpdate(sql3);
	      stMt.executeUpdate(sql2);
	      }catch(SQLException se){
	          //Handle errors for JDBC
	          se.printStackTrace();
	       }catch(Exception e){
	          //Handle errors for Class.forName
	          e.printStackTrace();
	       }
	      
	}
	/**
	public void insertValues()
	{
		try {
			for (int i=1; i<16;i++)
			{
				String insertAuthor = "INSERT INTO books.authors (authorID, first, last) VALUES (" + i+ ", 'F" + i +"', 'L"+i+"');";
				stMt.execute(insertAuthor);
				String insertPublisher = "INSERT INTO Books.publishers (publisherID, publisherName) VALUES (" + i + ", 'P" + i + "');";
				stMt.execute(insertPublisher);
				String insertTitles = "INSERT INTO Books.titles (isbn, title,publisherID, copyright) VALUES ('isbn" + i + "', 't" + i + "', " +i +" , "+ (2000+i)+");";
				stMt.execute(insertTitles);
				String insertISBN = "INSERT INTO Books.authorISBN (authorID, isbn) VALUES (" + i + ", 'isbn" + i + "');";
				stMt.execute(insertISBN);
						
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	**/
    public static String[] generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    public void insertValues()
    {
        try {
            String[] first = generateRandomWords(17);
            String[] last = generateRandomWords(17);
            String[] titles = generateRandomWords(17);
            for (int i=1; i<16;i++)
            {
                String insertAuthor = "INSERT INTO books.authors (authorID, first, last) VALUES (" + i+ ", '" + first[i] +"', '"+last[i]+"');";
                stMt.execute(insertAuthor);
                String insertPublisher = "INSERT INTO Books.publishers (publisherID, publisherName) VALUES (" + i + ", 'P" + i + "');";
                stMt.execute(insertPublisher);
                String insertTitles = "INSERT INTO Books.titles (isbn, title,publisherID, copyright) VALUES ('isbn" + i + "', '" + titles[i] + "', " +i +" , "+ (2000+i)+");";
                stMt.execute(insertTitles);
                String insertISBN = "INSERT INTO Books.authorISBN (authorID, isbn) VALUES (" + i + ", 'isbn" + i + "');";
                stMt.execute(insertISBN);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

//derp
}
