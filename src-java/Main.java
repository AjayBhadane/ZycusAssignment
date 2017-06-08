import java.io.*;

public class Main {

    public static void main(String[] args) {
        Map<Integer, String> map = new Map<>();
        map.put(1,"one");
        map.put(2,"two");

        System.out.println(map.get(1));

        FileOutputStream file = null;
        ObjectOutputStream oos = null;

        try{
            file = new FileOutputStream("map.ser");
            oos = new ObjectOutputStream(file);
            oos.writeObject(map);
        }
        catch(IOException ie){
            ie.printStackTrace();
        }
        finally {
            if(file != null){
                try{
                    file.close();
                }catch(IOException ie){
                    ie.printStackTrace();
                }
            }

            if(oos  != null){
                try{
                    oos.close();
                }catch(IOException ie){
                    ie.printStackTrace();
                }
            }
        }

        FileInputStream fin = null;
        ObjectInputStream ois = null;

        try{
            fin = new FileInputStream("map.ser");
            ois = new ObjectInputStream(fin);
            map = (Map)ois.readObject();
        }catch (IOException ie){
            ie.printStackTrace();
        }catch(SecurityException se){
            se.printStackTrace();
        }catch(ClassNotFoundException cnf){
            cnf.printStackTrace();
        }
        finally{
            if(fin!=null){
                try{
                    fin.close();
                }catch(IOException ie){
                    ie.printStackTrace();
                }
            }

            if(ois!=null){
                try{
                    ois.close();
                }catch(IOException ie){
                    ie.printStackTrace();
                }
            }
        }
        System.out.println(map.get(2));
    }
}