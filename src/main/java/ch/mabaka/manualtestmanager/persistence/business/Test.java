/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence.business;

import java.util.List;

import ch.mabaka.manualtestmanager.persistence.AbstractEntity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

/**
 * 
 */
public class Test extends AbstractEntity {
	
	@ManyToMany
	private List<TestSet> testSets;
	
	private String reference;
	
	private String preconditionDescripton;
	
	@OneToOne
	private Test preconditionTest;
	
	private String description;
	
	private String expectedResult;
	
	
	public List<TestSet> getTestSets() {
		return testSets;
	}

	public void setTestSets(List<TestSet> testSets) {
		this.testSets = testSets;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPreconditionDescripton() {
		return preconditionDescripton;
	}

	public void setPreconditionDescripton(String preconditionDescripton) {
		this.preconditionDescripton = preconditionDescripton;
	}

	public Test getPreconditionTest() {
		return preconditionTest;
	}

	public void setPreconditionTest(Test preconditionTest) {
		this.preconditionTest = preconditionTest;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
}
