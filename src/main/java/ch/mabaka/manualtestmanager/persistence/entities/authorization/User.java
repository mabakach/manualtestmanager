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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
