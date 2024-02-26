/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

/**
 *  Abstract base class for all entities.
 */
@MappedSuperclass
public abstract class AbstractEntity {
	
	@Id
    @GeneratedValue
    public Long sysid;
	
	public String sysInsertedBy;
	
	public String sysUpdatedBy;
	
	@Version
	public Long sysVersion;

	public AbstractEntity() {
		super();
	}

	public Long getSysid() {
		return sysid;
	}

	public void setSysid(Long sysid) {
		this.sysid = sysid;
	}

	public String getSysInsertedBy() {
		return sysInsertedBy;
	}

	public void setSysInsertedBy(String sysInsertedBy) {
		this.sysInsertedBy = sysInsertedBy;
	}

	public String getSysUpdatedBy() {
		return sysUpdatedBy;
	}

	public void setSysUpdatedBy(String sysUpdatedBy) {
		this.sysUpdatedBy = sysUpdatedBy;
	}

	public Long getSysVersion() {
		return sysVersion;
	}

	public void setSysVersion(Long sysVersion) {
		this.sysVersion = sysVersion;
	}

}