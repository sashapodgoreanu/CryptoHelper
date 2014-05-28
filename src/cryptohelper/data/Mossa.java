package cryptohelper.data;

public class Mossa {

    private char character;
    private char inverseChar;

    public Mossa(char character, char inverseChar) {
        this.character = character;
        this.inverseChar = inverseChar;
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

    @Override
    public String toString() {
        return "Mossa{" + "character=" + character + ", inverseChar=" + inverseChar + '}';
    }

}
