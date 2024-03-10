/**
 * 
 */
package ch.mabaka.manualtestmanager.filter;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import ch.mabaka.manualtestmanager.service.IdentityService;
import io.quarkus.security.runtime.SecurityIdentityAssociation;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestScoped
public class AuthorizationFilter implements Filter {

	@Inject
	SecurityIdentityAssociation identity;

	@Inject
	IdentityService identityService;
	
	@ConfigProperty(name = "quarkus.http.auth.form.login-page")
	String loginPage;

	@Inject
	Logger logger;

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest servletRequest = (HttpServletRequest) request;
		final String requestURI = removeLeadingSlash(servletRequest.getRequestURI());
		final boolean userIsAnonymous = identityService.isAnonymousUser();

		if (userIsAnonymous && (loginPage != null && !removeLeadingSlash(loginPage).equals(requestURI)
				&& (requestURI.isEmpty() || requestURI.startsWith("view") && requestURI.endsWith("html")
						|| requestURI.equals("index.xhtml")))) {
			logger.info(String.format("Requested URI was %s, but user is not logged in -> forward to %s!", requestURI,
					loginPage));
			final HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(loginPage);
			return;
		}
		if (chain != null) {
			chain.doFilter(request, response);
		}
	}

	private String removeLeadingSlash(final String inputString) {
		if (inputString != null && inputString.startsWith("/")) {
			return inputString.substring(1);
		}
		return inputString;
	}
}
