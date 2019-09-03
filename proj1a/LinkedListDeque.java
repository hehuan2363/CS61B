public class LinkedListDeque<NodeType>{
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node(0,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(NodeType x){
            sentinel = new Node(0,null,null);
            sentinel.next = new Node(x,sentinel,sentinel);
            sentinel.prev = sentinel.next;
            size = 1;
    }

    public void addFirst(NodeType item){
        Node first = sentinel.next;
        first.prev = new Node(item,sentinel,first);
        sentinel.next = first.prev;
        size = size + 1;
    }

    public void addLast(NodeType item){
        Node last = sentinel.prev;
        last.next = new Node(item,last,sentinel);
        sentinel.prev = last.next;
        size = size + 1;
    }

    public boolean isEmpty(){
        if(size <= 0){
            return true;
        }else{
            return false;
        }
    }

    public void printDeque(){
        Node p = sentinel;
        while(p.next != sentinel){
            p = p.next;
            System.out.println(p.data + " ");
        }
        System.out.println("\n");
    }

    public NodeType removeFirst(){
        Node first = sentinel.next;
        if(first == sentinel){
            return null;
        }else{
            sentinel.next = first.next;
            first.next.prev = sentinel;
            first.next = null;
            first.prev = null;
            size = size - 1;
            return (NodeType) first.data;
        }
    }

    public NodeType removeLast(){
        Node last = sentinel.prev;
        if(last == sentinel){
            return null;
        }else{
            sentinel.prev = last.prev;
            last.prev.next = sentinel;
            last.next = null;
            last.prev = null;
            size = size - 1;
            return (NodeType) last.data;
        }
    }

    public int size(){
        return size;
    }

    public NodeType get(int index){
        int i = -1;
        if(index >= size/2){
            i = size;
        }
        Node p = sentinel;
        while(i != index){
            if (index < size/2){
                p = p.next;
                i = i + 1;
            }else{
                p = p.prev;
                i = i - 1;
            }
        }
        return (NodeType) p.data;

    }


    private class Node<NodeType>{
        public NodeType data;
        public Node next;
        public Node prev;
        public Node(NodeType x, Node prevPtr, Node restPtr){
            data = x;
            prev = prevPtr;
            next = restPtr;
        }
    }

    public static void main(String [] args){
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(15);
        lld1.addFirst(23);
        lld1.addLast(25);

        lld1.printDeque();
        int i = lld1.get(2);
        System.out.println(" we are getting:  " + i );
        lld1.removeFirst();
        lld1.printDeque();
    }
}
