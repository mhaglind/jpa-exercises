package net.evolutionaryarchitecture.jpa.samples;

import java.util.Random;

public class KeyUtil {

    private static final char[] symbols = new char[36];

    private static final Random RND = new Random(System.currentTimeMillis());

    static {
        for (int idx = 0; idx < 10; ++idx)
            symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
            symbols[idx] = (char) ('a' + idx - 10);
    }

    private static synchronized String generateStringKey(int numberOfChars) {
        char[] buf = new char[numberOfChars];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[RND.nextInt(symbols.length)];
        return new String(buf);
    }

    public static synchronized String generateGuid(int charsPerGroup, int numberOfGroups) {
        StringBuffer sb = new StringBuffer(numberOfGroups * charsPerGroup + numberOfGroups);
        for (int i = 0; i < numberOfGroups; i++) {
            sb.append(generateStringKey(charsPerGroup));
            if (i == numberOfGroups - 1) {
                break;
            }
            sb.append('-');
        }
        return sb.toString();
    }

}
