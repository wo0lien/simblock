package simblock.task;

import simblock.block.EmptyBlock;
import simblock.block.NormalBlock;

import static simblock.simulator.Timer.getCurrentTime;

import simblock.node.Node;

public class MiningEmptyBlockTask extends AbstractMintingTask {
  public MiningEmptyBlockTask(Node minter, long interval) {
    super(minter, interval);
  }

  @Override
  public void run() {
    EmptyBlock createdBlock = null;
    if (this.getParent() instanceof NormalBlock) {
      createdBlock = new EmptyBlock((NormalBlock) this.getParent(), this.getMinter(), getCurrentTime());
    } else {
      createdBlock = new EmptyBlock((EmptyBlock) this.getParent(), this.getMinter(), getCurrentTime());
    }
    this.getMinter().receiveBlock(createdBlock);
  }
}