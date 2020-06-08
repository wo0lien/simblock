package simblock.block;

// import static simblock.simulator.Simulator.getSimulatedNodes;
// import static simblock.simulator.Simulator.getTargetInterval;
import static simblock.settings.EmptyBlocksConfiguration.N;

// import java.math.BigInteger;
import simblock.node.Node;
/**
 * Empty blocks are mined by the central entity when any normal block are mined.
 * It is always stack 0 with the same N^(-1) probability
 */
public class NormalBlock extends Block {
  public NormalBlock(EmptyBlock parent, Node minter, long time) {
    super(parent, minter, time);
    this.setCallValueStack(0);
    double n = N.doubleValue();
    this.setCallValueProba(Math.pow(n, -1));
  }
  public NormalBlock(NormalBlock parent, Node minter, long time) {
    super(parent, minter, time);
    this.setCallValueStack(0);
    double n = N.doubleValue();
    this.setCallValueProba(Math.pow(n, -1));
  }

  public static NormalBlock genesisBlock(Node minter) {
    return new NormalBlock((EmptyBlock)null, minter, 0);
  }
}