package ch.mabaka.manualtestmanager.persistence.operations.authorization;

import ch.mabaka.manualtestmanager.persistence.entities.authorization.User;
import ch.mabaka.manualtestmanager.persistence.operations.AbstractOperation;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserOperations extends AbstractOperation<User>{

	@Override
	public Class<User> getMyType() {
		return User.class;
	}

}
