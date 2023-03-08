package psbp.implementation.algorithm

import psbp.specification.computation.{Computation}

private[psbp] trait ParallelComposition[C[+_]: Computation, &&[+_, +_]]
    extends psbp.specification.algorithm.ParallelComposition[[Z, Y] =>> Z => C[Y], &&]:

  // internal declared

  private[psbp] def parallelComposition[Z, Y, X, W](
      `z>-->x`: Z => C[X],
      `y>-->w`: Y => C[W]
  ): (Z && Y) => C[X && W] = ???
