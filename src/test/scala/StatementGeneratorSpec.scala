import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import com.github.nscala_time.time.Imports._

class StatementGeneratorSpec extends AnyWordSpec with Matchers with MockFactory {

  "A statement" should {
    "print only the top row where the ledger is empty" in {
      val mockLedger = scala.collection.mutable.Set.empty[Transaction]
      StatementGenerator.print(mockLedger) shouldEqual "Amount      | Date        | Balance     "
    }
  }

/*  "A statement" should {
    "print only the top row where the ledger is empty" in {
      val transactionDate = new LocalDateTime("2022-07-27T11:39:45.618")
      val mockLedger = scala.collection.mutable.Set(Transaction(10, transactionDate))
    }
  }*/

}

