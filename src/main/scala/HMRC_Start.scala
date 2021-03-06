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
    // no items, cost = 0.0

    {



      // Test 1. Nothing
      {
        println("Simple Items:Test 1")

        val inputStream = Observable.just()

        val (tillTillElementStream, total, _, _) = Till.TillRoll(inputStream, typeMap)

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
      // (Orange, 25.0, ,25.0)
      // total = 25.0
      {
        println("Simple Items:Test 2")

        val inputStream = Observable.just("Orange")

        val (tillTillElementStream, total, _, _) = Till.TillRoll(inputStream, typeMap)

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

      // Test 2.1 Two for Three Orange
      // (Orange, 25.0, 25.0)
      // (Orange, 25.0, 3for2 part 1, 50.0) still charge for 1st orange
      // (Orange, 0.0, 3for2 part 2 free, 50.0)  do not charge for 3rd orange.
      // total = 50.0

      // Note tillItem is (type, cost, offer if any, total cost)
      // but can be changed easily.
      {
        println("Simple Items:Test 2")

        val inputStream = Observable.just("Orange","Orange")

        val (tillTillElementStream, total, _, _) = Till.TillRoll(inputStream, typeMap)

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

      // Test 3. Single Apple->Two for one
      // (Apple, 60.0, 60.0)
      // (Apple, 0, this is free, 60.0) // Do not change for this apple.
      // total = 60.0
      {
        println("Simple Items:Test 3")

        val inputStream = Observable.just("Apple")

        val (tillTillElementStream, total, _, _) = Till.TillRoll(inputStream, typeMap)

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
      // (Apple, 60.0, ,60.0)
      // (Apple, 0.0, get 2 nd free,60.0) //free
      // (Orange, 25, ,85.0)
      // (Orange, 25, 3 for 2 oranges ,110.0)
      // (Orange, 0.0, get this one free, 110.0)
      // (Apple, 60.0, 170.0)
      // (Apple, 0.0, free, 17.0)
      // (Apple, 60.0, 230.0)
      // (Apple, 0.0, free, 230.0)
      // total = 230.0
      {
        println("Simple Items:Test 4")

        val inputStream = Observable.just("Apple", "Orange", "Orange", "Apple", "Apple")

        val (tillTillElementStream, total, _, _) = Till.TillRoll(inputStream, typeMap)

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
