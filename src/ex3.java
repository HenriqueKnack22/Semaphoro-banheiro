import java.util.concurrent.Semaphore;

public class ex3 {
    public static void main(String args[]){
        Banheiro banheiro = new Banheiro();
        for(int i = 0; i<10; i++){
            Thread t = new Thread(new Pessoa(i,banheiro));
            t.start();
        }
    }
}

class Pessoa implements Runnable{
    private Banheiro banheiro;
    private int id;

    public Pessoa(int id, Banheiro banheiro){
        this.banheiro = banheiro;
        this.id = id++;
    }

    public void run(){
        System.out.println("Pessoa " +  id  + " quer usar o banheiro");
        banheiro.usar(id);
    }
}

class Banheiro {
    private Semaphore sem;

    public Banheiro(){
        sem = new Semaphore(3, false);
    }

    void usar(int id) {
    try{
        sem.acquire();
        System.out.println("Pessoa " + id + " Esta usando");
        Thread.sleep(5000);
        System.out.println("Pessoa " + id + " saiu");
        sem.release();
    }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    
    }
}
 