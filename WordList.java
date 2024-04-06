public abstract class WordList{
    protected int length;
    protected WordNode first;
    protected WordNode last;

    public WordList(){
        first = new WordNode(new Word(""));
        last = first;
        length = 0;
    }

    public void append(Word data){
        WordNode newNode = new WordNode(data);
        last.next = newNode;
        last = newNode;
        length++;
    }
    
    }
