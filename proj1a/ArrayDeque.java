public class ArrayDeque<ItemType> {
    private ItemType[] items;
    private int size;
    public ArrayDeque(){
        items = (ItemType[]) new Object[8];
        size = 0;
    }

    public void addFirst(ItemType item){
        for(int i=size;i>=1; i=i-1){
            items[i] = items[i-1];
        }
        items[0] = item;
        size = size + 1;
    }

    private void resize(int capacity){
        ItemType[] a = (ItemType[]) new Object[capacity];
        System.arraycopy(items,0,a,0,size);
        items = a;
    }

    public void addLast(ItemType item){
        if(size == items.length){
            resize(size * 2);
        }
        items[size] = item;
        size = size + 1;
    }

    public boolean isEmpty(){
        if(size <= 0){
            return true;
        }else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        for(int i=0; i < size; i=i+1){
            System.out.println(items[i] + " ");
        }
    }

    public ItemType removeFirst(){
        ItemType first = items[0];
        for(int i=0;i<size-1; i=i+1){
            items[i] = items[i+1];
        }
        items[size-1] = null;
        size = size - 1;
        return first;
    }

    public ItemType removeLast(){
        ItemType last = items[size - 1];
        items[size-1] = null;
        size = size - 1;
        return last;
    }

    public ItemType get(int index){
        return items[index];
    }


    public static void main(String [] args){
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(10);
        lld1.addFirst(15);
        lld1.addFirst(23);
        lld1.addLast(25);
        lld1.addLast(28);

        lld1.printDeque();
        int i = lld1.get(2);
        System.out.println(" we are getting:  " + i );
        lld1.removeFirst();
        lld1.printDeque();
    }


}
