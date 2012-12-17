learning-agent
==============
Compilation: javac *.java

Execution: java Simulation

The program will prompt the user for three variables:

1. C: the size of the corpus on which to train the agent. The agent will be shown C "desirable" actions and C "undesirable" actions. Each action has 7 effects, so the agent will be shown a total of C*14 effects.
2. V: the size of the environment into which the trained agent is placed. The graph will contain V nodes, and V*2 edges.
3. M: the number of moves the agent will be required to make. The agent will traverse M edges, resolving the effects of M actions.

The program will first create an environment. This involves creating 2 corpus files in the ./data directory (corpD.txt and corpU.txt), and a virtual action graph.

The program will then create an agent, and train the agent on the corpus files. During this process, the agent will create a hash in its memory detailing the probability each given effect (the 7 facets of actions) has of improving its status. After the learning process, a dump of this probability hash can be found at ./data/aMem.txt. The learning process itself is logged in ./data/log.txt.

The agent will then be placed at a random position in the environment graph and told to make the number of moves requested by the user. Each time it has to make a move, it looks at each possible next action, and evaluates the probability of the action improving its status. It does not actually calculate the effect the action will have, but looks at the sum of the "desirability probabilities" in the hash created in the previous step. While the agent tries to choose the action with the best chance of improving its status, to avoid looping it favors unexplored nodes. The order of choice is as follows:

1. Highest-probability unexplored node
2. Highest-probability explored node

There is currently no lookahead -- the agent simply chooses from the adjacent nodes.

Once the agent has chosen the best action available, it makes the move, and takes the associated benefits and/or detriments. Every move is logged in ./data/log.txt.
