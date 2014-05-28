package cryptohelper.data;

public class Mossa {

    private char character;
    private char inverseChar;

    public Mossa(char character, char inverseChar) {
        this.character = character;
        this.inverseChar = inverseChar;
    }

    public Mossa createMossaUndo() {
        char undoChar = inverseChar;
        if(undoChar>= 'a' && undoChar<= 'z'){
            undoChar = (char) (undoChar - 32);
        }
        return new Mossa(undoChar, character);
    }

    //METODI GETTER
    public char getCharacter() {
        return character;
    }

    public char getInverseChar() {
        return inverseChar;
    }

    //METODI SETTER
    public void setCharacter(char character) {
        this.character = character;
    }

    public void setInverseChar(char inverseChar) {
        this.inverseChar = inverseChar;
    }
}
