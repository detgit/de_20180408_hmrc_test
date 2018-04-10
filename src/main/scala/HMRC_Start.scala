import rx.lang.scala.Observable


object HMRC_Start {
  def main(args: Array[String]) : Unit = {
    // 1 st create tests.
    // We consider it as a stream over the items.
    // We also need to set up a table of distinct items and prices.
    //
    // Note would normally put this in a test system.
    // Just set up my computer from scratch do not have time.

    // These tests should just compile and fail.

    val typeMap = Map[String, Double](("Apple", 60.0),
      ("Orange", 25.0))


    // Simple No items.

    {



      // Test 1. Nothing
      {
        println("Simple Items:Test 1")

        val inputStream = Observable.just()

        val (tillTillElementStream, total) = Till.TillRoll(inputStream, typeMap)

        tillTillElementStream.subscribe(
          x => {
            println(s"$x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )

        total.subscribe(
          x => {
            println(s"final cost $x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )


        // wait till steam done.
      }


      // Test 2. Single Orange
      {
        println("Simple Items:Test 2")

        val inputStream = Observable.just("Orange")

        val (tillTillElementStream, total) = Till.TillRoll(inputStream, typeMap)

        tillTillElementStream.subscribe(
          x => {
            println(s"$x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )

        total.subscribe(
          x => {
            println(s"final cost $x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )

      }

      // Test 3. Single Apple
      {
        println("Simple Items:Test 3")

        val inputStream = Observable.just("Apple")

        val (tillTillElementStream, total) = Till.TillRoll(inputStream, typeMap)

        tillTillElementStream.subscribe(
          x => {
            println(s"$x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )

        total.subscribe(
          x => {
            println(s"final cost $x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )
      }


      // Test 4. Some random stuff, Apple, Orange, Orange, Apple, Apple.
      {
        println("Simple Items:Test 4")

        val inputStream = Observable.just("Apple", "Orange", "Orange", "Apple", "Apple")

        val (tillTillElementStream, total) = Till.TillRoll(inputStream, typeMap)

        tillTillElementStream.subscribe(
          x => {
            println(s"tillItem $x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )

        total.subscribe(
          x => {
            println(s"final cost $x")
          }
          ,
          e => {
            e.printStackTrace()
          }
          ,
          () => {
            println(s"done")
          }
        )

      }
    }
  }

}
