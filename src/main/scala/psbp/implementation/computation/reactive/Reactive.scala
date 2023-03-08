package psbp.implementation.computation.reactive

private[psbp] type Reactive = [Y] =>> (Y => Unit) => Unit

private[psbp] type `=>R` = [Z, Y] =>> Z => Reactive[Y]
