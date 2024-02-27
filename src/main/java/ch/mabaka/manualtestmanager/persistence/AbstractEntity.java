/**
 * 
 */
package ch.mabaka.manualtestmanager.persistence;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

/**
 * Abstract base class for all entities.
 */
@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@GeneratedValue
	public Long sysid;

	public String sysInsertedBy;

	public String sysUpdatedBy;

	@CreationTimestamp(source = SourceType.DB)
	public Date sysinsertts;

	@UpdateTimestamp(source = SourceType.DB)
	public Date sysupdatets;

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

	public Date getSysinsertts() {
		return sysinsertts;
	}

	public void setSysinsertts(Date sysinsertts) {
		this.sysinsertts = sysinsertts;
	}

	public Date getSysupdatets() {
		return sysupdatets;
	}

	public void setSysupdatets(Date sysupdatets) {
		this.sysupdatets = sysupdatets;
	}
}
