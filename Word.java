public class Word{
    public String word;
    
    public Word(String word){
        this.word = word;
    }
    public int compareTo (Word cool){
    return this.word.compareTo(cool.word);
        
    }

    public String getWord(){
        return word;
    }
}