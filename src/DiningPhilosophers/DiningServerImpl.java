package DiningPhilosophers;

public class DiningServerImpl implements DiningServer{
    // different philosopher states
    enum State {
        THINKING, HUNGRY, EATING
    };

    // number of philosophers
    public static final int NUM_OF_PHILS = 5;

    // array to record each philosopher's state
    private State[] state;

    public DiningServerImpl() {

    }

    // called by a philosopher when they wish to eat 
    @Override
    public void takeForks(int pnum) {
    
    }

    // called by a philosopher when they are finished eating 
    @Override
    public void returnForks(int pnum) {

    }
}
