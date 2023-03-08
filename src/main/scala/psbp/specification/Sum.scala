package psbp.specification

private[psbp] trait Sum[||[+_, +_]]:

  // internal declared

  private[psbp] def `y=>(y||z)`[Z, Y]: Y => (Y || Z)

  private[psbp] def `z=>(y||z)`[Z, Y]: Z => (Y || Z)

  private[psbp] def foldSum[Z, Y, X]: (X => Z) => (Y => Z) => (X || Y) => Z
