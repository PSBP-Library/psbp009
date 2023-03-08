package psbp.implementation.algorithm

import psbp.specification.computation.{Computation}

private[psbp] trait SequentialComposition[C[+_]: Computation]
    extends psbp.specification.algorithm.SequentialComposition[[Z, Y] =>> Z => C[Y]]:

  // internal defined

  private[psbp] override def sequentialComposition[Z, Y, X](
      `z=>c[y]`: Z => C[Y],
      `y=>c[x]`: => Y => C[X]
  ): Z => C[X] =
    z => `z=>c[y]`(z) >= { y => `y=>c[x]`(y) }
