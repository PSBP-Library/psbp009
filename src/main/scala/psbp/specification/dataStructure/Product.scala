package psbp.specification.dataStructure

import psbp.specification.function.{Function}

import psbp.specification.algorithm.{SequentialComposition}

private[psbp] trait Product[
    >-->[-_, +_]: SequentialComposition: [>-->[-_, +_]] =>> Function[>-->, &&],
    &&[+_, +_]: psbp.specification.Product
]:

  private lazy val summonedFunction = summon[Function[>-->, &&]]

  import summonedFunction.{functionLift, `z>-->z`}

  private lazy val summonedProduct = summon[psbp.specification.Product[&&]]

  import summonedProduct.{`(z&&y)=>z`, `(z&&y)=>y`, unfoldProduct}

  // external defined

  def `(z&&y)>-->z`[Z, Y]: (Z && Y) >--> Z = functionLift(`(z&&y)=>z`)

  def `(z&&y)>-->y`[Z, Y]: (Z && Y) >--> Y = functionLift(`(z&&y)=>y`)

  extension [Z, Y, X](`z>-->y`: Z >--> Y)
    def &&(`z>-->x`: => Z >--> X): Z >--> (Y && X) = product(`z>-->y`, `z>-->x`)

  extension [Z, Y, X, W](`z>-->x`: Z >--> X)
    def &&&(`y>-->w`: => Y >--> W): (Z && Y) >--> (X && W) =
      (`(z&&y)>-->z` >--> `z>-->x`) && (`(z&&y)>-->y` >--> `y>-->w`)

  def `(z&&y&&x)>-->z`[Z, Y, X]: (Z && Y && X) >--> Z =
    `(z&&y)>-->z` >--> `(z&&y)>-->z`

  def `(z&&y&&x)>-->y`[Z, Y, X]: (Z && Y && X) >--> Y =
    `(z&&y)>-->z` >--> `(z&&y)>-->y`

  def `(z&&y&&x)>-->x`[Z, Y, X]: (Z && Y && X) >--> X =
    `(z&&y)>-->y`

  def `(z&&y&&x)>-->(y&&x)`[Z, Y, X]: (Z && Y && X) >--> (Y && X) =
    `(z&&y&&x)>-->y` && `(z&&y)>-->y`

  def `(z&&y&&x)>-->(z&&x)`[Z, Y, X]: (Z && Y && X) >--> (Z && X) =
    `(z&&y&&x)>-->z` && `(z&&y)>-->y`

  def `(z&&y&&x)>-->(z&&y)`[Z, Y, X]: (Z && Y && X) >--> (Z && Y) =
    `(z&&y&&x)>-->z` && `(z&&y&&x)>-->y`

  // ...

  // internal declared

  private[psbp] def product[Z, Y, X](
      `z>-->y`: Z >--> Y,
      `z>-->x`: => Z >--> X
  ): Z >--> (Y && X)

  // internal defined

  private[psbp] def `z>-->(z&&z)`[Z]: Z >--> (Z && Z) = 
    `z>-->z` && `z>-->z`
  