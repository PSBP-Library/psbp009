package examples.specification

import psbp.specification.program.{Program}

def factorialProgram[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: BigInt >--> BigInt =

  lazy val summonedProgram: Program[>-->, &&, ||] = summon[Program[>-->, &&, ||]]

  import summonedProgram.{Let, If}

  If(isZeroProgram) Then {
    oneProgram
  } Else {
    Let {
      subtractOneProgram >--> factorialProgram
    } In {
      multiplyProgram
    }
  }
