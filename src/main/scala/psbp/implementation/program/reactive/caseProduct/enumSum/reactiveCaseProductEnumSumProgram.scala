package psbp.implementation.program.reactive.tuple2.either

import psbp.specification.program.{Program}

import psbp.implementation.program.reactive.{reactiveProgram}

import psbp.implementation.{CaseProduct, caseProduct, EnumSum, enumSum}

import psbp.implementation.computation.reactive.{`=>R`}

private[psbp] given reactiveCaseProductEnumSumProgram
    : Program[`=>R`, CaseProduct, EnumSum] =
  reactiveProgram[CaseProduct, EnumSum]
