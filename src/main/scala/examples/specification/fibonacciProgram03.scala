package examples.specification

import psbp.specification.program.{Program}

import examples.specification.{
  isZeroProgram,
  oneProgram,
  isOneProgram,
  subtractOneProgram,
  subtractTwoProgram,
  addProgram
}

def fibonacciProgram03[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: BigInt >--> BigInt =

  lazy val summonedProgram: Program[>-->, &&, ||] = summon[Program[>-->, &&, ||]]

  import summonedProgram.{Let, If, &&, &&&}

  If(isZeroProgram) Then {
    oneProgram
  } Else {
    If(isOneProgram) Then {
      oneProgram
    } Else {
      (subtractOneProgram && subtractTwoProgram) >-->
        (fibonacciProgram03 &&& fibonacciProgram03) >-->
        addProgram
    }
  }
