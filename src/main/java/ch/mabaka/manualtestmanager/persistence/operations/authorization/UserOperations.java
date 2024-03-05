package ch.mabaka.manualtestmanager.persistence.operations.authorization;

import ch.mabaka.manualtestmanager.persistence.entities.authorization.UserEntity;
import ch.mabaka.manualtestmanager.persistence.operations.AbstractOperation;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserOperations extends AbstractOperation<UserEntity>{

	@Override
	public Class<UserEntity> getMyType() {
		return UserEntity.class;
	}

}
