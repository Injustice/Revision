package org.injustice.revision.data;

/**
 * Created by Azmat on 24/03/2014.
 */
public enum History{
    TEHRAN_CONFERENCE("1943"),
    YALTA_CONFERENCE("1945"),
    USE_OF_ATOMIC_BOMB("1945"),
    POTSDAM_CONFERENCE("1945"),
    MARSHALL_AID("1946"),
    TRUMAN_DOCTRINE("1946"),
    START_OF_THE_COLD_WAR("1947"),
    COMINFORM("1947"),
    NATO("1949"),
    COMECON("1949"),
    WARSAW_PACT("1955"),
    HUNGARIAN_UPRISING("1956"),
    CUBAN_REVOLUTION("1959"),
    BERLIN_WALL_BUILT("1961"),
    BAY_OF_PIGS("1961"),
    CUBAN_MISSILE_CRISIS("1962"),
    OUTER_SPACE_TREATY("1967"),
    PRAGUE_SPRING("1968"),
    NUCLEAR_NON_PROLIFERATION_TREATY("1968"),
    BREZHNEV_DOCTRINE("1968"),
    SALT_TALKS_I("1972"),
    HELSINKI_CONFERENCE("1975"),
    JOHN_PAUL_POLAND_VISIT("1979"),
    INVASION_OF_AFGHANISTAN("1979"),
    CARTER_DOCTRINE("1980"),
    US_BOYCOTT("1980"),
    EVIL_EMPIRE_SPEECH("1983"),
    SOVIET_BOYCOTT("1984"),
    NEW_SOVIET_THINKING("1985"),
    GENEVA_SUMMIT("1985"),
    REYKJAVIK_SUMMIT("1986"),
    CHERNOBYL_CRISIS("1986"),
    INF_TREATY("1987"),
    BERLIN_WALL_COLLAPSE("1989"),
    SINATRA_DOCTRINE("1989"),
    MOSCOW_UPRISING("1991"),
    END_OF_THE_COLD_WAR("1991");

    private String answer;
    private String toString;
    private final String[] ignoreCapitals = {"of", "the", "built"};

    History(String answer) {
        this.answer = answer;
    }

    public String answer() {
        return answer;
    }

    @Override
    public String toString() {
        if (toString != null) { return toString; }
        final String name = name();
        final String[] words = name.replace('$', '\'').split("_+");
        final StringBuilder result = new StringBuilder(name.length());
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            final String newWord = performCapitalization(word, i);
            if (newWord != null) {
                result.append(newWord);
            }
            if(i < words.length - 1){
                result.append(' ');
            }
        }

        return (toString = result.toString());
    }

    private String performCapitalization(final String oldWord, final int index) {
        if(oldWord == null || oldWord.length() == 0){
            return null;
        }
        boolean ignore = false;
        if (index != 0) {
            for (final String str : ignoreCapitals) {
                if(str.equalsIgnoreCase(oldWord)){
                    ignore = true;
                    break;
                }
            }
        }
        final String newWord = oldWord.toLowerCase();
        if(ignore){
            return oldWord.toLowerCase();
        }
        return Character.toUpperCase(newWord.charAt(0)) + newWord.substring(1);
    }
}
