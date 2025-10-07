package io.arvn.atm.shadow_bank.cli;

import io.arvn.atm.shadow_bank.core.util.ConsoleUtil;

import java.util.Scanner;

public final class CLIUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static <T> T leerLinea(String mensajeEntrada, Class<T> tipoSalida) {
        ConsoleUtil.writeMessage(mensajeEntrada);
        String input = scanner.nextLine();

        Object resultado = switch (tipoSalida.getSimpleName()) {
            case "String" -> input;
            case "Integer" -> Integer.parseInt(input);
            case "Double" -> Double.parseDouble(input);
            case "Float" -> Float.parseFloat(input);
            case "Long" -> Long.parseLong(input);
            case "Boolean" -> Boolean.parseBoolean(input);
            case "Short" -> Short.parseShort(input);
            case "Byte" -> Byte.parseByte(input);
            case "Character" -> {
                if (input.length() != 1) {
                    throw new IllegalArgumentException("Se esperaba un solo carácter.");
                }
                yield input.charAt(0);
            }
            default -> throw new UnsupportedOperationException("Tipo no soportado: " + tipoSalida.getSimpleName());
        };

        return tipoSalida.cast(resultado);
    }

    public static void mostrarMensaje(String mensajeEntrada) {
        ConsoleUtil.writeMessage(mensajeEntrada);
    }
}
