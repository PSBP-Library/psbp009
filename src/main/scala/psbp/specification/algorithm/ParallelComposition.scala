package psbp.specification.algorithm

import psbp.specification.function.{Function}

import psbp.specification.dataStructure.{Product}

trait ParallelComposition[
    >-->[-_, +_]
      : [>-->[-_, +_]] =>> Function[>-->, &&]
      : SequentialComposition
      : [>-->[-_, +_]] =>> Product[>-->, &&],
    &&[+_, +_]
]:

  private lazy val summonedFunction = summon[Function[>-->, &&]]

  import summonedFunction.{`z>-->z`}

  private lazy val summonedProduct = summon[Product[>-->, &&]]

  import summonedProduct.{`(z&&y)>-->y`, `z>-->(z&&z)`}

  // external defined

  extension [Z, Y, X, W](`z>-->x`: Z >--> X)
    def |&&&|(`y>-->w`: Y >--> W): (Z && Y) >--> (X && W) =
      parallelComposition(`z>-->x`, `y>-->w`)

  extension [Z, Y, X](`z>-->y`: Z >--> Y)
    def |&&|(`z>-->x`: Z >--> X): Z >--> (Y && X) =
      `z>-->(z&&z)` >--> (`z>-->y` |&&&| `z>-->x`)

  def async[Z, Y](`z>-->y`: Z >--> Y): Z >--> Y =
    (`z>-->z` && `z>-->y`) >--> `(z&&y)>-->y`

  // internal declared

  private[psbp] def parallelComposition[Z, Y, X, W](
      `z>-->x`: Z >--> X,
      `y>-->w`: Y >--> W
  ): (Z && Y) >--> (X && W)
