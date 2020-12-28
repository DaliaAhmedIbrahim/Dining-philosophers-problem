package DiningPhilosophers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiningServerImpl implements DiningServer{
    // different philosopher states
    enum State {
        THINKING, HUNGRY, EATING
    };

    // number of philosophers
    public static final int NUM_OF_PHILS = 5;

    // array to record each philosopher's state
    private final State[] state;
    private final ReentrantLock entLock;
    private final Condition self[];


    public DiningServerImpl() {
        int num = 5;
        self = new Condition[num];
	state = new State[num];
	entLock=new ReentrantLock();
        
	for(int i=0; i<num; i++){
            
                self[i]=entLock.newCondition();
		state[i]=State.THINKING;
                
	}
        
    }

    // called by a philosopher when they wish to eat 
    @Override
    public void takeForks(int pnum) {
        entLock.lock();
        try {
            state[pnum] = State.HUNGRY;
            test(pnum);
            
            if (state[pnum] != State.EATING)
                try {
                    self[pnum].await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DiningServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
        } finally {
            entLock.unlock();
        }
        
    }

    // called by a philosopher when they are finished eating 
    @Override
    public void returnForks(int pnum) {
        
        entLock.lock();
        try {
            state[pnum] = State.THINKING;
            test((pnum + 1) % 5);
            test((pnum + 4) % 5);
        } finally {
            entLock.unlock();
        }

    }
    
    private void test(int pnum) 
    { 
  
        if (state[(pnum + 1) % 5] != State.EATING && state[(pnum + 4) % 5] != State.EATING && state[pnum] == State.HUNGRY) { 
            
            state[pnum] = State.EATING; 
            self[pnum].signal(); 
        } 
    } 
    public void initialization_code() { 
        
        for (int pnum = 0; pnum < 5; pnum++) 
            state[pnum] = State.THINKING; 
        
    } 
    public int LeftNighbor(int i ){
        
       return (i + 1) % 5;
       
    }
    
    public int RighttNighbor(int i ){
        
       return (i + 4) % 5;
       
    }
    
}
