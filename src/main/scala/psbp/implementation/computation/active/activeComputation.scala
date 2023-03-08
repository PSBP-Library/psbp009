package psbp.implementation.computation.active

import psbp.specification.computation.{Computation}

private[psbp] given activeComputation: Computation[Active] with

  private[psbp] override def expressionLift[Z]: Z `=>A` Z = z => z

  private[psbp] override def bind[Z, Y]: Active[Z] => (Z `=>A` Y) => Active[Y] = `a[z]` =>
    `z=>a[y]` => `z=>a[y]`(`a[z]`)
