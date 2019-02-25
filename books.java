 package jdbcdemo;
 
 //Stuff to have withing object
 //0. AUTHOR FIRST NAME
 //1. Author LAST name
 //2. Author ID
 //3. Publisher name
 //4. Publisher ID
 //5. ISBN
 //6. TITLE
 //6. EDITION
 //7. COPYRIGHT YEAR
 //8. PRICE
public class books {
	String firstName;
	String lastName;
	String publisher;
	int publisherID;
	int authorID;
	char ISBN;
	String title;
	int edition;
	int copyright;
	float price;
	public books(String firstName, String lastName, String publisher, int publisherID, int authorID, char ISBN, String title, int edition, int copyright, float price){
		this.firstName=firstName;
		this.lastName=lastName;
		this.publisher=publisher;
		this.publisherID=publisherID;
		this.authorID=authorID;
		this.ISBN=ISBN;
		this.title=title;
		this.edition=edition;
		this.copyright=edition;
		this.price=price;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getlastName(){
		return lastName;
	}
	public String getPublisher(){
		return publisher;
	}
	public int getPublisherID(){
		return publisherID;
	}
	public int authorID(){
		return authorID;
	}
	public char getISBN()
	{
		return ISBN;
	}
	public String getTitle(){
		return title;
	}
	public int getEdition(){
		return edition;
	}
	public int getCopyright(){
		return copyright;
	}
	public float getPrice(){
		return price;
	}
}
