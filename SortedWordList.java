public class SortedWordList extends WordList {
    public SortedWordList(){
        super();
    }
    public void add(Word cat){
        append(cat);
    }
    
        public void addSorted(Word w) {
            WordNode newNode = new WordNode(w);
            WordNode current = last.next;
            WordNode prev = last;
            while (current != null && current.data.getWord().compareTo(w.getWord()) < 0) {
                prev = current;
                current = current.next;
            }
    
            prev.next = newNode;
            newNode.next = current;
    
            if (current == null)
                last = newNode;
    
            length++;
        }
    }