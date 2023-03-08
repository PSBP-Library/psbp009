package psbp.implementation.program.active

import psbp.implementation.computation.active.{Active, `=>A`}

import psbp.implementation.computation.active.{activeComputation}

private[psbp] given activeProgram[
    &&[+_, +_]: psbp.specification.Product,
    ||[+_, +_]: psbp.specification.Sum
]: psbp.specification.program.Program[`=>A`, &&, ||]
  with psbp.implementation.program.Program[Active, &&, ||]
  with {}
