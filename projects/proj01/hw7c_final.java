




/**
 * A searcher for charactrs in a sting
 */
public class CharacterSearcher
{
    private String text;

    /**
     * Constructs a CharacterSearcher object
     * @param s the text
     */
    public CharacterSearcher(String s) {
        text = s;
    }

    /**
     * Gets the text for this CharacterSearcher
     * @return the text for this CharacterSearcher
     */
    public String getText()
    {
        return text;
    }

    /**
     * Gets the number of p's and q's (both upper and lower case p's and q's) in the text
     * @return the number of p's and q's (both upper and lower case p's and q's) in the text
     */
    public int countPAndQs() {
        String lower = text.toLowerCase();
        int counter = 0;
        for(int i = 0; i < lower.length(); i++) {
            String letter = lower.substring(i, i+1);
            if("pq".contains(letter)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Gets the numbers of nonletter in the text.
     * @return the numbers of nonletter in the text.
     */
    public int countNonLetters() {
        int counter = 0;
        for(int i = 0; i < text.length(); i++){
            if(!isLetter(text.substring(i, i+1))) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Gets the count of how many letters appear more than once.
     * @return the count of how many letters appear more than once.
     */
    public int countDuplicates() {
        int count = 0;
        String lowerCase = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            boolean flag = false;
            String character = lowerCase.substring(i, i + 1);
            for (int j = i + 1; j < text.length(); j++) {               
                String character2 = lowerCase.substring(j, j + 1);                
                if (character.equals(character2) && !flag) {
                        flag = true;
                        count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Determines if the string consists of a single letter
     * @param in the string to check. 
     * @return false if the string is not a single character 
     * of if that character is not a letter otherwise returns 
     * true
     */
    public static boolean isLetter(String in)
    {
        if (in.length() > 1)
        {
            return false;
        }

        return Character.isLetter(in.charAt(0));
    }
}
