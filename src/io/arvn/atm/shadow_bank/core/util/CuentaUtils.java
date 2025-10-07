package io.arvn.atm.shadow_bank.core.util;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class CuentaUtils {
    private static final SecureRandom random = new SecureRandom();
    private static final Set<String> seedsUsadas = new HashSet<>();

    public static String generateSeed() {
        String seed;
        do {
            int numero = 10000000 + random.nextInt(90000000);
            seed = String.valueOf(numero);
        } while (seedsUsadas.contains(seed));

        seedsUsadas.add(seed);
        return seed;
    }
}

