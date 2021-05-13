package peliculas;

public class StringUtils {
    public static String capitalize(String cadena) {
        String result = cadena.toLowerCase();
        char[] charArray = result.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {

                if (foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }

        result = String.valueOf(charArray);
        return result;
    }
}
