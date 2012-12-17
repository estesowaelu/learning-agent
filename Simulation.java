// Master program
/////////////////////////////////////////////////////////////

public class Simulation {

    static Environment environment;
    static Agent agent;

    public Simulation(int cSize, int eSize) {
        environment = new Environment(cSize, eSize);
        agent = new Agent(eSize);
    }

    public static void main(String[] args) {
        // get input from command line for c and e
        In in = new In();
        System.out.print("How big should the corpi be? ");
        int cSize = in.readInt();
        System.out.print("How big should the environment be? ");
        int eSize = in.readInt();
        System.out.print("How many moves should the agent make? ");
        int moves = in.readInt();

        // make a new simulation with the given sizes
        Simulation simulation = new Simulation(cSize, eSize);

        // let the agent learn about the environment
        agent.learn(environment);

        // make the agent choose the best path through the environment
        agent.choose(environment, moves);

        System.out.println("\nAgent log in ./data/log.txt");
    }

}
