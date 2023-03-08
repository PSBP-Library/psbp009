package psbp.implementation.dataStructure

import psbp.specification.function.{Function}

import psbp.specification.algorithm.{SequentialComposition}

import psbp.specification.computation.{Computation}

private[psbp] trait DataStructure[
    C[+_]: Computation: [C[+_]] =>> Function[[Z, Y] =>> Z => C[Y], &&]: [C[
        +_
    ]] =>> SequentialComposition[[Z, Y] =>> Z => C[Y]],
    &&[+_, +_]: psbp.specification.Product,
    ||[+_, +_]: psbp.specification.Sum
] extends psbp.implementation.dataStructure.Product[C, &&],
      psbp.implementation.dataStructure.Sum[C, ||],
      psbp.specification.dataStructure.DataStructure[[Z, Y] =>> Z => C[Y], &&, ||]
