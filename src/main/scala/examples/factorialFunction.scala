package examples

def factorialFunction: BigInt => BigInt =
  z =>
    if isZeroFunction(z)
    then oneFunction(z)
    else
      val y = factorialFunction(subtractOneFunction(z))
      multiplyFunction((z, y))
