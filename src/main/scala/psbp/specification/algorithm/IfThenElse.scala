package psbp.specification.algorithm

import psbp.specification.function.{Function}

import psbp.specification.dataStructure.{Sum}

private[psbp] trait IfThenElse[
    >-->[-_, +_]
      : [>-->[-_, +_]] =>> Function[>-->, &&]
      : SequentialComposition
      : [>-->[-_, +_]] =>> LocalDefinition[>-->, &&]
      : [>-->[-_, +_]] =>> Sum[>-->, ||],
    &&[+_, +_]: psbp.specification.Product,
    ||[+_, +_]: psbp.specification.Sum
]:

  private lazy val summonedFunction = summon[Function[>-->, &&]]

  import summonedFunction.{functionLift}

  private lazy val summonedLocalDefinition = summon[LocalDefinition[>-->, &&]]

  import summonedLocalDefinition.{Let}

  private lazy val summonedFunctionLevelProduct = summon[psbp.specification.Product[&&]]

  import summonedFunctionLevelProduct.{`(z&&y)=>z`, `(z&&y)=>y`}

  private lazy val summonedFunctionLevelSum = summon[psbp.specification.Sum[||]]

  import summonedFunctionLevelSum.{`y=>(y||z)`, `z=>(y||z)`}

  // external defined

  def If[Z, Y](`z>-->b`: Z >--> Boolean): Then[Z, Y] =
    new:
      override def Then(`z>-t->y`: => Z >--> Y): Else[Z, Y] =
        new:
          override def Else(`z>-f->y`: => Z >--> Y): Z >--> Y =
            Let {
              `z>-->b`
            } In {
              `z>-t->y` OrElse `z>-f->y`
            }

  // internal declared

  private[psbp] trait Then[Z, Y]:
    def Then(`z>-t->y`: => Z >--> Y): Else[Z, Y]

  private[psbp] trait Else[Z, Y]:
    def Else(`z>-f->y`: => Z >--> Y): Z >--> Y

  // internal defined

  private[psbp] def ifThenElse[Z, Y](
      `z>-t->y`: => Z >--> Y,
      `z>-f->y`: => Z >--> Y
  ): (Z && Boolean) >--> Y =
    (functionLift[Z && Boolean, Z || Z] { `z&&b` =>
      if (`(z&&y)=>y`(`z&&b`))
      then `y=>(y||z)`(`(z&&y)=>z`(`z&&b`))
      else `z=>(y||z)`(`(z&&y)=>z`(`z&&b`))
    }) >--> (`z>-t->y` || `z>-f->y`)
      
  extension [Z, Y](`z>-t->y`: => Z >--> Y)
    private[psbp] def OrElse(`z>-f->y`: => Z >--> Y): (Z && Boolean) >--> Y =
      ifThenElse(`z>-f->y`, `z>-f->y`)
