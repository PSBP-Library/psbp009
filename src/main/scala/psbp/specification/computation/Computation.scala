package psbp.specification.computation

private[psbp] trait Computation[C[+_]]:

  // internal declared

  private[psbp] def expressionLift[Z]: Z => C[Z]

  private[psbp] def bind[Z, Y]: C[Z] => (Z => C[Y]) => C[Y]

  // internal defined

  extension [Z, Y](`c[z]`: C[Z])
    private[psbp] def >=(`z=>c[y]`: => Z => C[Y]): C[Y] = bind(`c[z]`)(`z=>c[y]`)
