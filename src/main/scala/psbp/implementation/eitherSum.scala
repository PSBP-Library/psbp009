package psbp.implementation

import psbp.specification.{Sum}

private[psbp] given eitherSum: Sum[Either] with

  private[psbp] override def `y=>(y||z)`[Z, Y]: Y => Either[Y, Z] = z => Left(z)

  private[psbp] override def `z=>(y||z)`[Z, Y]: Z => Either[Y, Z] = y => Right(y)

  private[psbp] override def foldSum[Z, Y, X]: (X => Z) => (Y => Z) => Either[X, Y] => Z =
    `x=>z` =>
      `y=>z` =>
        case Left(x) =>
          `x=>z`(x)
        case Right(y) =>
          `y=>z`(y)
