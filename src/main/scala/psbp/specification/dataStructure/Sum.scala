package psbp.specification.dataStructure

private[psbp] trait Sum[>-->[-_, +_], ||[+_, +_]]:

  // internal declared

  private[psbp] def sum[Z, Y, X](
      `x>-->z`: => X >--> Z,
      `y>-->z`: => Y >--> Z
  ): (X || Y) >--> Z

  // internal defined

  extension [Z, Y, X](`x>-->z`: => X >--> Z)
    private[psbp] def ||(`y>-->z`: => Y >--> Z): (X || Y) >--> Z = sum(`x>-->z`, `y>-->z`)
