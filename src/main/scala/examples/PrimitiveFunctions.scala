package examples

private[examples] val isZeroFunction: BigInt => Boolean = z => z == 0

private[examples] val isOneFunction: BigInt => Boolean = z => z == 1

private[examples] def oneFunction[Z]: Z => BigInt = _ => 1

private[examples] val subtractOneFunction: BigInt => BigInt = z => z - 1

private[examples] val subtractTwoFunction: BigInt => BigInt = z => z - 2

private[examples] val addFunction: Tuple2[BigInt, BigInt] => BigInt = (z, y) => z + y

private[examples] val multiplyFunction: Tuple2[BigInt, BigInt] => BigInt = (z, y) => z * y
