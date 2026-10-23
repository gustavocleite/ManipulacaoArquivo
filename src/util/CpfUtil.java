/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author gustavo
 */

public class CpfUtil {

    public static String removerMascara(String cpf) {
        if (cpf == null) {
            return "";
        }

        return cpf.replaceAll("\\D", "");
    }

    public static String adicionarMascara(String cpf) {
        cpf = removerMascara(cpf);

        if (cpf.length() != 11) {
            return cpf;
        }

        return cpf.substring(0, 3) + "."
                + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-"
                + cpf.substring(9, 11);
    }

    public static boolean validarCpf(String cpf) {
        cpf = removerMascara(cpf);

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;

        for (int i = 0; i < 9; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (10 - i);
        }

        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        soma = 0;

        for (int i = 0; i < 10; i++) {
            int numero = Character.getNumericValue(cpf.charAt(i));
            soma += numero * (11 - i);
        }

        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return digito1 == Character.getNumericValue(cpf.charAt(9))
                && digito2 == Character.getNumericValue(cpf.charAt(10));
    }
}
