package examples.specification

import psbp.specification.program.{Program}

import examples.{
  isZeroFunction,
  isOneFunction,
  oneFunction,
  subtractOneFunction,
  subtractTwoFunction,
  addFunction,
  multiplyFunction
}

def isZeroProgram[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: BigInt >--> Boolean =
  lazy val summonedProgram = summon[Program[>-->, &&, ||]]
  import summonedProgram.functionLift
  functionLift(isZeroFunction)

def isOneProgram[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: BigInt >--> Boolean =
  lazy val summonedProgram = summon[Program[>-->, &&, ||]]
  import summonedProgram.functionLift
  functionLift(isOneFunction)

def oneProgram[
    Z,
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: Z >--> BigInt =
  lazy val summonedProgram = summon[Program[>-->, &&, ||]]
  import summonedProgram.functionLift
  functionLift(oneFunction)

def subtractOneProgram[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: BigInt >--> BigInt =
  lazy val summonedProgram = summon[Program[>-->, &&, ||]]
  import summonedProgram.functionLift
  functionLift(subtractOneFunction)

def subtractTwoProgram[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: BigInt >--> BigInt =
  lazy val summonedProgram = summon[Program[>-->, &&, ||]]
  import summonedProgram.functionLift
  functionLift(subtractTwoFunction)

def addProgram[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: (BigInt && BigInt) >--> BigInt =
  lazy val summonedProgram = summon[Program[>-->, &&, ||]]
  import summonedProgram.functionFromTuple2Lift
  functionFromTuple2Lift(addFunction)

def multiplyProgram[
    >-->[-_, +_]: [_[-_, +_]] =>> Program[>-->, &&, ||],
    &&[+_, +_],
    ||[+_, +_]
]: (BigInt && BigInt) >--> BigInt =
  lazy val summonedProgram = summon[Program[>-->, &&, ||]]
  import summonedProgram.functionFromTuple2Lift
  functionFromTuple2Lift(multiplyFunction)
