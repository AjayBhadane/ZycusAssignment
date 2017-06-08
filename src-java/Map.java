import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ajay on 6/8/2017.
 */
public class Map<K, V> implements Serializable{
    private int size;
    private int DEFAULT_SIZE = 20;

    private mEntry<K, V>[] values = new mEntry[DEFAULT_SIZE];

    public synchronized V get(K key){
        for(int i = 0; i < size;i++){
            if(! values[i].getKey().equals(null)){
                if(values[i].getKey().equals(key)){
                    return values[i].getValue();
                }
            }
        }
        return null;
    }

    public synchronized void put(K key, V value){
        boolean insert =true;

        for (int i = 0; i < size; i++){
            if(values[i].getKey().equals(key)){
                values[i].setValue(value);
                insert = false;
            }
        }

        if(insert){
            increseCapacity();
            values[size++] = new mEntry<>(key, value);
        }
    }

    private void increseCapacity(){
        if(size == values.length){
            int newSize = values.length * 2;
            values = Arrays.copyOf(values,  newSize);
        }
    }

    public int getSize(){return size;}

    public void removeKey(K key){
        for(int i = 0; i < size; i++){
            if(values[i].getKey().equals(key)){
                values[i] = null;
                size--;
                condenceArray(i);
            }
        }
    }

    private void condenceArray(int start){
        for(int i = start ; i < size; i++){
            values[i] = values[i+1];
        }
    }

    public Set<K> keySet(){
        Set<K> set = new HashSet<>();
        for(int i = 0;i < size;i++){
            set.add(values[i].getKey());
        }
        return set;
    }
}
