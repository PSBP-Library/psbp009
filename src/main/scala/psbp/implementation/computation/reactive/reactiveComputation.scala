package psbp.implementation.computation.reactive

import psbp.specification.computation.{Computation}

private[psbp] given reactiveComputation: Computation[Reactive] with

  private[psbp] override def expressionLift[Z]: Z `=>R` Z = z => `z=>u` => `z=>u`(z)

  private[psbp] override def bind[Z, Y]: Reactive[Z] => (Z `=>R` Y) => Reactive[Y] =
    `r[z]` => `z=>r[y]` => `y=>u` => `r[z]` { z => `z=>r[y]`(z)(`y=>u`) }
