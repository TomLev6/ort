public class MyArrayList<T> {

    protected Object[] array;
    protected int size;

    public MyArrayList(Object[] array, int index) {
        this.array = new Object[10];
        this.size = 0;
    }

    public void add(Object element) {
        if (this.size==this.arraySize()){
            Object[] tmp = new Object[arraySize() + 10];
            int i =0;
            for (Object x:this.array) {
                tmp[i] = x;
                i++;
            }
            this.array = tmp;
        }
        this.array[this.size] = element;
        this.size++;
    }

    public void remove(int index) {
        if(this.array[index]!=null){
            this.array[index] = null;
        }
    }
    public int arraySize()
    {
        return this.array.length;
    }
    public void clear(){
        for (Object x:this.array) {
            x = null;
        }
    }
    public Object[] subList(int i, int j){
        int k,o=0;
        Object[] tmp = new Object[arraySize()];
        for (k=i;k<=j;k++){
            if (this.array[k]!=null){
                tmp[o] = this.array[k];
                o++;
            }
        }
        return tmp;
    }
}