package simblock.settings;

import java.math.BigInteger;

public class EmptyBlocksConfiguration {
  /**
   * According to the article
   * An upper bound 𝑁 on the maximum admissible number of contending blocks, that can be set as large as possible.
   */
  public static final BigInteger N = BigInteger.ONE.add(BigInteger.ONE).pow(32);
  /**
   * According to the article
   * an integer k
   */
  public static final int k = 12; //8
  
}