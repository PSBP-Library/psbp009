package psbp.implementation

import psbp.specification.{Product}

private[psbp] given tuple2Product: Product[Tuple2] with

  private[psbp] override def `(z&&y)=>y`[Z, Y]: Tuple2[Z, Y] => Y = { case (z, y) => y }

  private[psbp] override def `(z&&y)=>z`[Z, Y]: Tuple2[Z, Y] => Z = { case (z, y) => z }

  private[psbp] override def unfoldProduct[Z, Y, X]
      : (Z => Y) => (Z => X) => Z => Tuple2[Y, X] =
    `z=>y` => `z=>x` => z => (`z=>y`(z), `z=>x`(z))
