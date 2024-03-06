package ch.mabaka.manualtestmanager.service.authorization;

import ch.mabaka.manualtestmanager.persistence.entities.authorization.User;
import ch.mabaka.manualtestmanager.persistence.operations.AbstractOperation;
import ch.mabaka.manualtestmanager.persistence.operations.authorization.UserOperations;
import ch.mabaka.manualtestmanager.service.AbstractService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class UserService extends AbstractService<User> {
	
	@Inject
	UserOperations userOperations;

	@Override
	protected AbstractOperation<User> getOperations() {
		return userOperations;
	}

}
