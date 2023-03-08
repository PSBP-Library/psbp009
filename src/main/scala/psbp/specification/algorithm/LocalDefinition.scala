package psbp.specification.algorithm

import psbp.specification.function.{Function}

import psbp.specification.dataStructure.{Product}

private[psbp] trait LocalDefinition[
    >-->[-_, +_]: [>-->[-_, +_]] =>> Function[>-->, &&]
                : SequentialComposition
                : [>-->[-_, +_]] =>> Product[>-->, &&],
    &&[+_, +_]
]:

  private lazy val summonedFunction = summon[Function[>-->, &&]]

  import summonedFunction.{`z>-->z`}

  // external defined

  def Let[Z, Y, X](`z>-->y`: Z >--> Y): In[Z, Y, X] =
    new:
      override def In(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X =
        (`z>-->z` && `z>-->y`) >--> `(z&&y)>-->x`

  // internal declared

  private[psbp] trait In[Z, Y, X]:
    def In(`(z&&y)>-->x`: => (Z && Y) >--> X): Z >--> X
