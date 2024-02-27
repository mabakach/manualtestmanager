package ch.mabaka.manualtestmanager;

import org.junit.jupiter.api.Test;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordTest {

	@Test
	public void testPassword() {
		System.out.println(BcryptUtil.bcryptHash("Password"));
	}

}
