/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence.entities.authorization;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import io.quarkus.security.jpa.RolesValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * 
 */
@Entity
@Table(schema="public", name="role")
public class RoleEntity extends AbstractEntity {

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users;

    @RolesValue
    private String role;
	
	public RoleEntity() {
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
