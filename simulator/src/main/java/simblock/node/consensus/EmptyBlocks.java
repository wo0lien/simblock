package simblock.node.consensus;

import static simblock.simulator.Main.random;
import static simblock.settings.SimulationConfiguration.INTERVAL;

import simblock.block.Block;
import simblock.block.EmptyBlock;
import simblock.block.NormalBlock;
import simblock.node.Node;
import simblock.task.AbstractMintingTask;
import simblock.task.MiningEmptyBlockTask;
import simblock.task.MiningNormalBlockTask;

public class EmptyBlocks extends AbstractConsensusAlgo {
  public EmptyBlocks(Node selfNode) {
    super(selfNode);
  }

  /**
   * Determine if yes or not the block can be mined or not. If the block can't be
   * mined it returns null
   */
  public MiningNormalBlockTask minting() {
    Node selfNode = this.getSelfNode();
    Block parent = selfNode.getBlock();
    double callValueProba = parent.getCallValueProba();
    double p = random.nextDouble();
    long inter = (long) (1.0 / selfNode.getMiningPower());
    return p <= callValueProba ? new MiningNormalBlockTask(selfNode, inter) : null;
  }

  /**
   * Schedule the next empty block mining if nothing happens
   */
  @Override
  public AbstractMintingTask mintingEmptyBlock() {
    Node selfNode = this.getSelfNode();
    return new MiningEmptyBlockTask(selfNode, INTERVAL);
  }

  /**
   * Check if the received block is valid or no
   */
  public boolean isReceivedBlockValid(Block receivedBlock, Block currentBlock) {
    // block must be a normal block or an empty block
    if ((receivedBlock instanceof NormalBlock) && (currentBlock == null || (receivedBlock.getTime() > currentBlock.getTime()))){
      return true;
    }
    if ((receivedBlock instanceof EmptyBlock)
        && (currentBlock == null || receivedBlock.getTime() > currentBlock.getTime() )) {
      return true;
    }
    return false;
  }

  public Block genesisBlock() {
    return NormalBlock.genesisBlock(this.getSelfNode());
  }
}