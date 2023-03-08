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

def fibonacciProgram01[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: BigInt >--> BigInt =

  lazy val summonedProgram: Program[>-->, &&, ||] = summon[Program[>-->, &&, ||]]

  import summonedProgram.{Let, If, `(z&&y)>-->z`, `(z&&y&&x)>-->(y&&x)`}

  If(isZeroProgram) Then {
    oneProgram
  } Else {
    If(isOneProgram) Then {
      oneProgram
    } Else {
      Let {
        subtractOneProgram >--> fibonacciProgram01
      } In {
        Let {
          `(z&&y)>-->z` >--> subtractTwoProgram >--> fibonacciProgram01
        } In {
          `(z&&y&&x)>-->(y&&x)` >--> addProgram
        }
      }
    }
  }
