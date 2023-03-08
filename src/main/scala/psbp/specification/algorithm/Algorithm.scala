package psbp.specification.algorithm

private[psbp] trait Algorithm[>-->[-_, +_], &&[+_, +_], ||[+_, +_]]
    extends IfThenElse[>-->, &&, ||],
      LocalDefinition[>-->, &&],
      SequentialComposition[>-->],
      ParallelComposition[>-->, &&]
