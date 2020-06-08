package simblock.node;

import static simblock.simulator.Timer.putTask;

import simblock.node.Node;
import simblock.task.AbstractMintingTask;

public class CentralAuthority extends Node {
  public CentralAuthority(int numConnection, String routingTableName, String consensusAlgoName)
  {
    super(1, numConnection, 1, 1, routingTableName, consensusAlgoName);
  }

  @Override
  public void minting() {
    AbstractMintingTask task = this.getConsensusAlgo().mintingEmptyBlock();
    this.mintingTask = task;
    if (task != null) {
      putTask(task);
    }
  }
}