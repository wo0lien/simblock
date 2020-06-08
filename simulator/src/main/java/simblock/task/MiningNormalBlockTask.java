package simblock.task;

import static simblock.simulator.Timer.getCurrentTime;

import simblock.block.EmptyBlock;
import simblock.block.NormalBlock;

// import static simblock.simulator.Timer.getCurrentTime;

// import java.math.BigInteger;
// import simblock.block.NormalBlock;
import simblock.node.Node;

public class MiningNormalBlockTask extends AbstractMintingTask {
  public MiningNormalBlockTask(Node minter, long interval) {
    super(minter, interval);
  }

  @Override
  public void run() {
    NormalBlock createdBlock = null;
    if (this.getParent() instanceof NormalBlock) {
      createdBlock = new NormalBlock((NormalBlock) this.getParent(), this.getMinter(), getCurrentTime());
    } else {
      createdBlock = new NormalBlock((EmptyBlock) this.getParent(), this.getMinter(), getCurrentTime());
    }
    this.getMinter().receiveBlock(createdBlock);
  }
}