package psbp.implementation.computation.active

private[psbp] type Active = [Y] =>> Y

private[psbp] type `=>A` = [Z, Y] =>> Z => Active[Y]
