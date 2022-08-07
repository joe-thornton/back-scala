import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import com.github.nscala_time.time.Imports._

class StatementGeneratorSpec extends AnyWordSpec with Matchers with MockFactory {

  "A statement" should {
    "print only the top row where the ledger is empty" in {
      val mockLedger = scala.collection.mutable.Set.empty[Transaction]
      StatementGenerator.print(mockLedger) shouldEqual "Amount, Date, Balance\n"
    }
  }

  "A statement" should {
    "print an amount, date and balance for single transaction" in {
      val mockTransaction = Transaction(100, new LocalDateTime("2022-07-27T11:39:45.618"))
      val mockLedger = scala.collection.mutable.Set(mockTransaction)
      val expectedString = "Amount, Date, Balance\n100.00 | 27/07/22 | 100.00\n"
      StatementGenerator.print(mockLedger) shouldEqual expectedString
    }
  }

}

