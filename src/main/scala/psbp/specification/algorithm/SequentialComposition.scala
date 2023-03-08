package psbp.specification.algorithm

private[psbp] trait SequentialComposition[>-->[-_, +_]]:

  // external defined

  extension [Z, Y, X](`z>-->y`: Z >--> Y)
    def >-->(`y>-->x`: => Y >--> X): Z >--> X = sequentialComposition(`z>-->y`, `y>-->x`)

  // internal declared

  private[psbp] def sequentialComposition[Z, Y, X](
      `z>-->y`: Z >--> Y,
      `y>-->x`: => Y >--> X
  ): Z >--> X
