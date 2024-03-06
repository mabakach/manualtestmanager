/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence.entities.business;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import dev.morphia.annotations.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * 
 */
@Entity
@Table(schema="public", name="testset")
public class TestSet extends AbstractEntity {

	private String name;
	
	@ManyToMany
	private List<Test> tests;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	
	
	
}
