package pl.studentmed.facultative.models.validators;

public class ValueObject {

    public static final String NAME_REGEX = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]*$";

    public static boolean containsLettersAndSpaces(String value) {
        return value.chars()
                .allMatch(character -> Character.isLetter(character) || Character.isSpaceChar(character));
    }

    public static boolean constainsLettersDigitsAndSpaces(String value) {
        return value.chars()
                .allMatch(character -> Character.isLetterOrDigit(character) || Character.isSpaceChar(character));
    }

}
