package psbp.specification

private[psbp] trait Product[&&[+_, +_]]:

  // internal declared

  private[psbp] def `(z&&y)=>z`[Z, Y]: (Z && Y) => Z

  private[psbp] def `(z&&y)=>y`[Z, Y]: (Z && Y) => Y

  private[psbp] def unfoldProduct[Z, Y, X]: (Z => Y) => (Z => X) => (Z => (Y && X))
