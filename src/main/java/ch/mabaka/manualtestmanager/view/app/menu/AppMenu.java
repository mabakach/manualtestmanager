/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ch.mabaka.manualtestmanager.view.app.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ch.mabaka.manualtestmanager.persistence.entities.authorization.RoleName;
import ch.mabaka.manualtestmanager.service.IdentityService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class AppMenu {

	@Inject
	IdentityService identityService;

	List<MenuCategory> menuCategories;
	List<MenuItem> menuItems;

	@PostConstruct
	public void init() {
		menuCategories = new ArrayList<>();
		menuItems = new ArrayList<>();

		if (identityService.getLoggedInUserRoles().contains(RoleName.ADMIN.code)) {
			List<MenuItem> adminMenu = new ArrayList<>();
			adminMenu.add(new MenuItem("Users", "/view/administration/users/users"));

			menuCategories.add(new MenuCategory("Adminstration", adminMenu));
		}
		
		if (!identityService.isAnonymousUser()) {
			List<MenuItem> basicsMenu = new ArrayList<>();
			basicsMenu.add(new MenuItem("Introduction", "/view/ajax/basic"));

			menuCategories.add(new MenuCategory("Basics", basicsMenu));
		}  else {
//			List<MenuItem> loginMenu = new ArrayList<>();
//			loginMenu.add(new MenuItem("Login", "/view/login/login"));
//
//			menuCategories.add(new MenuCategory("Login", loginMenu));
		}

		for (MenuCategory category : menuCategories) {
			for (MenuItem menuItem : category.getMenuItems()) {
				menuItem.setParentLabel(category.getLabel());
				if (menuItem.getUrl() != null) {
					menuItems.add(menuItem);
				}
				if (menuItem.getMenuItems() != null) {
					for (MenuItem item : menuItem.getMenuItems()) {
						item.setParentLabel(menuItem.getLabel());
						if (item.getUrl() != null) {
							menuItems.add(item);
						}
					}
				}
			}
		}
	}

	public List<MenuItem> completeMenuItem(String query) {
		String queryLowerCase = query.toLowerCase();
		List<MenuItem> filteredItems = new ArrayList<>();
		for (MenuItem item : menuItems) {
			if (item.getUrl() != null && (item.getLabel().toLowerCase().contains(queryLowerCase)
					|| item.getParentLabel().toLowerCase().contains(queryLowerCase))) {
				filteredItems.add(item);
			}
			if (item.getBadge() != null) {
				if (item.getBadge().toLowerCase().contains(queryLowerCase)) {
					filteredItems.add(item);
				}
			}
		}
		filteredItems.sort(Comparator.comparing(MenuItem::getParentLabel));
		return filteredItems;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public List<MenuCategory> getMenuCategories() {
		return menuCategories;
	}
}