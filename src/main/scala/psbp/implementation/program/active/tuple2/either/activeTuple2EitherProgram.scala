package psbp.implementation.program.active.tuple2.either

import psbp.specification.program.{Program}

import psbp.implementation.program.active.{activeProgram}

import psbp.implementation.{tuple2Product, eitherSum}

import psbp.implementation.computation.active.{`=>A`}

private[psbp] given activeTuple2EitherProgram: Program[`=>A`, Tuple2, Either] =
  activeProgram[Tuple2, Either]
