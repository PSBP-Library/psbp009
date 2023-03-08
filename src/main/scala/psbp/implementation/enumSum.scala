package psbp.implementation

import psbp.specification.{Sum}

enum EnumSum[+Z, +Y] {
  case Left(z: Z) extends EnumSum[Z, Y]
  case Right(y: Y) extends EnumSum[Z, Y]
}

import EnumSum.{Left, Right}

private[psbp] given enumSum: Sum[EnumSum] with

  private[psbp] override def `y=>(y||z)`[Z, Y]: Y => EnumSum[Y, Z] = z => Left(z)

  private[psbp] override def `z=>(y||z)`[Z, Y]: Z => EnumSum[Y, Z] = y => Right(y)

  private[psbp] override def foldSum[Z, Y, X]
      : (X => Z) => (Y => Z) => EnumSum[X, Y] => Z =
    `x=>z` =>
      `y=>z` =>
        case Left(x) =>
          `x=>z`(x)
        case Right(y) =>
          `y=>z`(y)
