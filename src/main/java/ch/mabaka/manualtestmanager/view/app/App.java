/*
 * Copyright 2009-2021 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.mabaka.manualtestmanager.view.app;

import java.io.Serializable;
import java.util.Locale;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import ch.mabaka.manualtestmanager.domain.Country;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Named
@SessionScoped
public class App implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	Logger logger;
	
	@Inject
    private FacesContext facesContext;
	
	@ConfigProperty(name = "quarkus.http.auth.form.login-page")
	String loginPage;
	
	private String theme = "saga";
    private boolean darkMode = false;
    private String inputStyle = "outlined";
    private Country locale = new Country(0, Locale.US);

    public String getTheme() {
        return theme;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getInputStyle() {
        return inputStyle;
    }

    public void setInputStyle(String inputStyle) {
        this.inputStyle = inputStyle;
    }

    public String getInputStyleClass() {
        return this.inputStyle.equals("filled") ? "ui-input-filled" : "";
    }

	public Country getLocale() {
		return locale;
	}

	public void setLocale(Country locale) {
		this.locale = locale;
	}

    public void changeTheme(String theme, boolean darkMode) {
        this.theme = theme;
        this.darkMode = darkMode;
		//VirtualMachine.getMemoryStatisticsAsString();
    }

    public String getThemeImage() {
    	String result = getTheme();
    	switch (result) {
		case "nova-light":
			result = "nova.png";
			break;
		case "nova-colored":
			result = "nova-accent.png";
			break;
		case "nova-dark":
			result = "nova-alt.png";
			break;
		case "bootstrap4-blue-light":
            result = "bootstrap4-light-blue.svg";
            break;
        case "bootstrap4-blue-dark":
            result = "bootstrap4-dark-blue.svg";
            break;
        case "bootstrap4-purple-light":
            result = "bootstrap4-light-purple.svg";
            break;
        case "bootstrap4-purple-dark":
            result = "bootstrap4-dark-purple.svg";
            break;
        default:
            result += ".png";
			break;
		}
    	return result;
    }
    
    public String logout() {
        final String result = loginPage;
        
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                System.out.println(c.getName());
                if (c.getName().equals("quarkus-credential")) {
                    System.out.println("Deleting cookie");
                    c.setPath("/");
                    c.setMaxAge(0);
                    response.addCookie(c);
                    break;
                }
            }
            request.logout();
            facesContext.getExternalContext().invalidateSession();
        } catch (ServletException e) {
        	logger.warn("Exception during logout:", e);
        }
        
        return result;
    }
}
