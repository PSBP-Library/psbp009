package psbp.implementation

import psbp.specification.{Product}

case class CaseProduct[+Z, +Y](z: Z, y: Y)

private[psbp] given caseProduct: Product[CaseProduct] with

  private[psbp] override def `(z&&y)=>z`[Z, Y]: CaseProduct[Z, Y] => Z =
    case CaseProduct(z, y) => z

  private[psbp] override def `(z&&y)=>y`[Z, Y]: CaseProduct[Z, Y] => Y =
    case CaseProduct(z, y) => y

  private[psbp] override def unfoldProduct[Z, Y, X]
      : (Z => Y) => (Z => X) => Z => CaseProduct[Y, X] =
    `z=>y` => `z=>x` => z => CaseProduct(`z=>y`(z), `z=>x`(z))
