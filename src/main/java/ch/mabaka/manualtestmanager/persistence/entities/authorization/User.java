/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence.entities.authorization;

import java.util.ArrayList;
import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

/**
 * 
 */
@Entity
@Table(schema="public", name="user")
@UserDefinition
public class User extends AbstractEntity {

	@Username
	private String username;
	
	@Password
	private String password;
	
	private String fullname;
	
	@Email(message = "Email invalid")
	private String email;

	@ManyToMany
    @Roles
    private  List<Role> roles = new ArrayList<>();
	
	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eMail) {
		this.email = eMail;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
