package simblock.block;

// import static simblock.simulator.Simulator.getSimulatedNodes;
// import static simblock.simulator.Simulator.getTargetInterval;

import static simblock.settings.EmptyBlocksConfiguration.N;
import static simblock.settings.EmptyBlocksConfiguration.k;

// import java.math.BigInteger;
import simblock.node.Node;

public class EmptyBlock extends Block {

  public EmptyBlock(EmptyBlock parent, Node minter, long time) {
    super(parent, minter, time);
    //Get the parent callValue stack and increment
    this.setCallValueStack(this.getParent().getCallValueStack() + 1);
    //Set the callValue Proba with formula
    double n = N.doubleValue();
    //Use the article probability formula
    double callValue = Math.pow(n, ((double)this.getCallValueStack() / (double)k) - 1.0);
    this.setCallValueProba(callValue);
  }
  public EmptyBlock(NormalBlock parent, Node minter, long time) {
    super(parent, minter, time);
    //Get the parent callValue stack and increment
    this.setCallValueStack(this.getParent().getCallValueStack() + 1);
    //Set the callValue Proba with formula
    double n = N.doubleValue();
    //Use the article probability formula
    double callValue = Math.pow(n, ((double)this.getCallValueStack() / (double)k) - 1.0);
    this.setCallValueProba(callValue);
  }
}