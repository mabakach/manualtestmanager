/**
 * 
 */
package ch.mabaka.manualtestmanager.service;

import java.security.Principal;
import java.util.Set;

import io.quarkus.security.runtime.SecurityIdentityAssociation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 * Service to retrieve the current logged in user
 */
@RequestScoped
public class IdentityService {

	@Inject
	SecurityIdentityAssociation securityIdentityAscsociation;
	
	public Principal getLoggedInPrincipal() {
		if (securityIdentityAscsociation != null && securityIdentityAscsociation.getIdentity() != null) {
			return securityIdentityAscsociation.getIdentity().getPrincipal();
		}
		return null;
	}
	
	public String getLoggedInUserName() {
		final Principal principal = getLoggedInPrincipal();
		
		if (principal != null) {
			return principal.getName();
		}
		return null;
	}
	
	public Set<String> getLoggedInUserRoles(){
		if (securityIdentityAscsociation != null && securityIdentityAscsociation.getIdentity() != null) {
			return securityIdentityAscsociation.getIdentity().getRoles();
		}
		return Set.of();
	}
	
	public boolean isAnonymousUser() {
		final String userName = getLoggedInUserName();
		return userName == null || userName.isEmpty();
	}
}
