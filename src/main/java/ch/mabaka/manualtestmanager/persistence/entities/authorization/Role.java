/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence.entities.authorization;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import io.quarkus.security.jpa.RolesValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
@Entity
@Table(schema="public", name="role")
public class Role extends AbstractEntity {

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
	
	public Role() {
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}
	
	@RolesValue
	public String getRoleNameCode() {
		return roleName.code;
	}
}
