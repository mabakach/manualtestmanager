/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence.entities.business;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import jakarta.persistence.ManyToMany;

/**
 * 
 */
public class TestSet extends AbstractEntity {

	private String name;
	
	@ManyToMany
	private List<Test> tests;
	
}
