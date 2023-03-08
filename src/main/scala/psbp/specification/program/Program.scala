package psbp.specification.program

import psbp.specification.function.{Function}

import psbp.specification.algorithm.{Algorithm}

import psbp.specification.dataStructure.{DataStructure}

trait Program[>-->[-_, +_], &&[+_, +_], ||[+_, +_]]
    extends Function[>-->, &&],
      Algorithm[>-->, &&, ||],
      DataStructure[>-->, &&, ||]
