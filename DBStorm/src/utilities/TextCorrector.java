package utilities;

public class TextCorrector {

	public String removeFirstAndLastSpace(String entrada) {
		int tamaño = entrada.length();
		for (int i = 0; i < tamaño; i++) {
			Character aux = entrada.charAt(0);
			if (aux.equals(' ')) {
				entrada = entrada.substring(1, entrada.length());
			}
			if (entrada.charAt(entrada.length() - 1) == ' ') {
				entrada = entrada.substring(0, entrada.length() - 1);
			}
		}

		return entrada;

	}

	public String removeFLSPassword(char[] password) {
		String unifiedPassword = "";
		for (int i = 0; i < password.length; i++) {
			unifiedPassword += password[i];
		}
		int tamaño = unifiedPassword.length();
		for (int i = 0; i < tamaño; i++) {
			Character aux = unifiedPassword.charAt(0);
			if (aux.equals(' ')) {
				unifiedPassword = unifiedPassword.substring(1, unifiedPassword.length());
			}
			if (unifiedPassword.charAt(unifiedPassword.length() - 1) == ' ') {
				unifiedPassword = unifiedPassword.substring(0, unifiedPassword.length() - 1);
			}
		}

		return unifiedPassword;
	}

}
