package psbp.specification.dataStructure

private[psbp] trait DataStructure[>-->[-_, +_], &&[+_, +_], ||[+_, +_]]
    extends Product[>-->, &&],
      Sum[>-->, ||]
