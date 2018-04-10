import rx.lang.scala.Observable

import scala.collection.immutable.HashMap

object Till {

  def
  TillRoll(inputStream: Observable[String], typeMap: Map[String, Double])
  : ( Observable[(String, Double, String, Double)],  Observable[Double], Observable[(String, Double, String)], Observable[HashMap[String, Int]] )
  = {

    val groupbyCountOutStream = inputStream
      .scan(HashMap[String, Int]())((x, y) => x.+(y -> (x.getOrElse(y, 0) + 1))).drop(1)

    val itemCostStream = inputStream.map(x => (x, typeMap(x)))

    val costStreamPostOffer = itemCostStream.zip(groupbyCountOutStream)
      .map({ case (a, x) =>
        if (a._1 == "Apple")
          Observable.just[(String, Double, String)]((a._1, a._2, "so_bogof_1"), (a._1, 0.0d, "so_bogof_2_free")) // special offer buy one get one free
        else if (a._1 == "Orange")
          if (((x.get(a._1).get) % 2) == 0)
            Observable.just[(String, Double, String)]((a._1, a._2, "so_3for2_1 " + (x.get(a._1).get)), (a._1, 0.0d, "so_3for2_2_free"))
          else
            Observable.just[(String, Double, String)]((a._1, a._2, " " + (x.get(a._1).get)))
        else
          Observable.just[(String, Double, String)]((a._1, a._2, ""))
      }).flatten

    val subTotalStream = costStreamPostOffer.map(x => x._2).scan(0.0d)((a, b) => a + b).drop(1)

    val tillElementStream = Observable.zip(
      costStreamPostOffer.map(x => x._1), costStreamPostOffer.map(x => x._2), costStreamPostOffer.map(x => x._3), subTotalStream)

    val total = tillElementStream.map({ case (x1, x2, x3, x4) => x4 }).lastOrElse(0.0d)
    ( tillElementStream, total, costStreamPostOffer, groupbyCountOutStream)
  }

}
