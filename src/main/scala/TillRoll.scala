import rx.lang.scala.Observable

object Till {

  def
  TillRoll(inputStream: Observable[String], typeMap: Map[String, Double])
  : ( Observable[(String, Double, Double)],  Observable[Double] )
  = {
    val costStream = inputStream.map[Double](x => typeMap(x))

    val totalStream = costStream.scan(0.0d)((a, b) => a + b).drop(1)

    val tillTillElementStream = inputStream
      .zipWith(costStream)({ case (x1, x2) => (x1, x2) })
      .zipWith(totalStream)({ case ((x1, x2), x3) => (x1, x2, x3) })

    val total = tillTillElementStream.map({ case (x1, x2, x3) => x3 }).lastOrElse(0.0d)
    ( tillTillElementStream, total)
  }

}
