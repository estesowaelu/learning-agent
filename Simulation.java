// Master program
/////////////////////////////////////////////////////////////
// 1. instantiate Generator, to build basis files for unique simulation
// 2. instantiate Learner, to process files into a cohesive memory
// 3. instantiate Environment, to build graph of available Actions
// 4. instantiate Agent, to traverse graph and choose the best path

public class Simulation {

    Generator generator;
    Learner learner;
    Environment environment;
    Agent agent;

    public Simulation() {
        generator = new Generator();
        learner = new Learner();
        environment = new Environment();
        agent = new Agent();
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation;

        generator.generate();
        learner.learn();
        environment.build();
        agent.choose();
    }

}
