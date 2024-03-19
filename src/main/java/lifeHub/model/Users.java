package lifeHub.model;

//CREATE TABLE Users (
//	    UserId INT AUTO_INCREMENT PRIMARY KEY,
//	    UserName VARCHAR(255),
//	    FirstName VARCHAR(255),
//	    LastName VARCHAR(255),
//	    Email VARCHAR(255),
//	    PasswordHash VARCHAR(255)
//	);

// Class Instance
public class Users {
	private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;    
    private String passwordHash;
	
	// Constructor
    public Users(int userId, String userName, String firstName, String lastName, String email, String passwordHash) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.passwordHash = passwordHash;
	}
    
    // Getters and Setters
    public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

    // toString
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", passwordHash=" + passwordHash + "]";
	}
}
