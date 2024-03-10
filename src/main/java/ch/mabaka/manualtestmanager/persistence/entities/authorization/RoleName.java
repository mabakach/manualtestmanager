package ch.mabaka.manualtestmanager.persistence.entities.authorization;

public enum RoleName {
	
	ADMIN("admin"), USER("user");
	
	public final String code;

    private RoleName(String code) {
        this.code = code;
    }
}
