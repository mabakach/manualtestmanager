package ch.mabaka.manualtestmanager.service.authorization;

import java.util.ArrayList;
import java.util.List;

import ch.mabaka.manualtestmanager.persistence.entities.authorization.User;
import ch.mabaka.manualtestmanager.persistence.operations.AbstractOperation;
import ch.mabaka.manualtestmanager.persistence.operations.authorization.UserOperations;
import ch.mabaka.manualtestmanager.service.AbstractService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class UserService extends AbstractService<User> {
	
	@Inject
	UserOperations userOperations;

	@Override
	protected AbstractOperation<User> getOperations() {
		return userOperations;
	}
	
	@Transactional
	public List<User> getAllUsersDetachedWithoutPassword(){
		List<User> users = new ArrayList<>();
		users.addAll(userOperations.findAllAndDetach());
		users.forEach(u -> u.setPassword(null));
		return users;
	}
}
