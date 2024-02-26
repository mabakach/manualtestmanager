/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence.authorization;

import java.util.ArrayList;
import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

/**
 * 
 */
@Entity
@UserDefinition
public class UserEntity extends AbstractEntity {

	@Username
	private String username;
	
	@Password
	private String password;

	@ManyToMany
    @Roles
    private  List<RoleEntity> roles = new ArrayList<>();
	
	public UserEntity() {
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

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
}
