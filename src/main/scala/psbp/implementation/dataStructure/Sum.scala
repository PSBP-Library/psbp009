package psbp.implementation.dataStructure

import psbp.specification.computation.{Computation}

private[psbp] trait Sum[C[+_]: Computation, ||[+_, +_]: psbp.specification.Sum]
    extends psbp.specification.dataStructure.Sum[[Z, Y] =>> Z => C[Y], ||]:

  private type >--> = [Z, Y] =>> Z => C[Y]

  private lazy val summonedSum = summon[psbp.specification.Sum[||]]

  import summonedSum.{foldSum}

  // internal defined

  private[psbp] def sum[Z, Y, X](
      `x>-->z`: => X >--> Z,
      `y>-->z`: => Y >--> Z
  ): (X || Y) >--> Z = foldSum(`x>-->z`)(`y>-->z`)
