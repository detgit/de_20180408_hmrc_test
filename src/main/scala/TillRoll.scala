import rx.lang.scala.Observable

object Till {

  def
  TillRoll(inputStream: Observable[String], typeMap: Map[String, Double])
  : ( Observable[(String, Double, String, Double)],  Observable[Double] )
  = {
    ( Observable.just(), Observable.just( 0.0 ))
  }

}
