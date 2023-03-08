package psbp.implementation.function

import psbp.specification.dataStructure.{Product}

import psbp.specification.computation.{Computation}

private[psbp] trait Function[
    C[+_]: Computation: [C[+_]] =>> Product[[Z, Y] =>> Z => C[Y], &&],
    &&[+_, +_]: psbp.specification.Product
] extends psbp.specification.function.Function[[Z, Y] =>> Z => C[Y], &&]:

  private type >--> = [Z, Y] =>> Z => C[Y]

  private lazy val summonedComputation = summon[Computation[C]]

  import summonedComputation.{expressionLift}

  private lazy val summonedProduct = summon[Product[[Z, Y] =>> Z => C[Y], &&]]

  import summonedProduct.{
    `(z&&y)>-->z` => `(z&&y)=>c[z]`,
    `(z&&y)>-->y` => `(z&&y)=>c[y]`,
    `(z&&y&&x)>-->z` => `(z&&y&&x)=>c[z]`,
    `(z&&y&&x)>-->y` => `(z&&y&&x)=>c[y]`,
    `(z&&y&&x)>-->x` => `(z&&y&&x)=>c[x]`
  }

  private lazy val summonedFunctionLevelProduct = summon[psbp.specification.Product[&&]]

  import summonedFunctionLevelProduct.{unfoldProduct}

  // external defined

  override def functionLift[Z, Y]: (Z => Y) => (Z >--> Y) = `z=>y` =>
    z => expressionLift(`z=>y`(z))

  override def functionFromTuple2Lift[Z, Y, X]
      : (Tuple2[Z, Y] => X) => ((Z && Y) => C[X]) =
    `tuple2[z,y]=>x` =>
      `z&&y` =>
        `(z&&y)=>c[z]`(`z&&y`) >= { z =>
          `(z&&y)=>c[y]`(`z&&y`) >= { y =>
            expressionLift(`tuple2[z,y]=>x`(z, y))
          }
        }

  override def functionFromTuple3Lift[Z, Y, X, W]
      : (Tuple3[Z, Y, X] => W) => ((Z && Y && X) => C[W]) =
    `tuple3[z,y,x]=>w` =>
      `z&&y&&x` =>
        `(z&&y&&x)=>c[z]`(`z&&y&&x`) >= { z =>
          `(z&&y&&x)=>c[y]`(`z&&y&&x`) >= { y =>
            `(z&&y&&x)=>c[x]`(`z&&y&&x`) >= { x =>
              expressionLift(`tuple3[z,y,x]=>w`(z, y, x))
            }
          }
        }
