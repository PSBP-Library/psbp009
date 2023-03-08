package psbp.implementation.dataStructure

import psbp.specification.function.{Function}

import psbp.specification.algorithm.{SequentialComposition}

import psbp.specification.computation.{Computation}

private[psbp] trait Product[
    C[+_]: Computation
         : [C[+_]] =>> SequentialComposition[[Z, Y] =>> Z => C[Y]]
         : [C[+_]] =>> Function[[Z, Y] =>> Z => C[Y], &&], 
    &&[+_, +_]: psbp.specification.Product
] extends psbp.specification.dataStructure.Product[[Z, Y] =>> Z => C[Y], &&]:

  private lazy val summonedComputation = summon[Computation[C]]

  import summonedComputation.{expressionLift}

  private lazy val summonedProduct = summon[psbp.specification.Product[&&]]

  import summonedProduct.{unfoldProduct}

  // external defined

  private[psbp] override def product[Z, Y, X](
      `z=>c[y]`: Z => C[Y],
      `z=>c[x]`: => Z => C[X]
  ): Z => C[Y && X] =
    z =>
      `z=>c[y]`(z) >= { y =>
        `z=>c[x]`(z) >= { x =>
          expressionLift(unfoldProduct(_ => y)(_ => x)(()))
        }
      }
