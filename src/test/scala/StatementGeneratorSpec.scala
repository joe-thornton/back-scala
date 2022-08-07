import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory
import com.github.nscala_time.time.Imports._

class StatementGeneratorSpec extends AnyWordSpec with Matchers with MockFactory {

  "A statement" should {
    "print only the top row where the ledger is empty" in {
      val mockLedger = scala.collection.mutable.ArrayBuffer.empty[Transaction]
      StatementGenerator.print(mockLedger) shouldEqual "Amount | Date | Balance\n"
    }
  }

  "A statement" should {
    "print an amount, date and balance for single transaction" in {
      val mockTransaction = Transaction(100, new LocalDateTime("2022-07-27T11:39:45.618"))
      val mockLedger = scala.collection.mutable.ArrayBuffer(mockTransaction)
      val expectedString = "Amount | Date | Balance\n100.00 | 27/07/22 | 100.00\n"
      StatementGenerator.print(mockLedger) shouldEqual expectedString
    }
  }

  "A statement" should {
    "print multiple transactions with a balance, sorted by date" in {
      val mockTransaction1 = Transaction(-100, new LocalDateTime("2022-07-27T11:39:45.618"))
      val mockTransaction2 = Transaction(50, new LocalDateTime("2022-07-28T11:39:45.618"))
      val mockTransaction3 = Transaction(200, new LocalDateTime("2022-07-26T11:39:45.618"))
      val mockLedger = scala.collection.mutable.ArrayBuffer(mockTransaction1, mockTransaction2, mockTransaction3)
      val expectedString = "Amount | Date | Balance\n200.00 | 26/07/22 | 200.00\n-100.00 | 27/07/22 | 100.00\n50.00 | 28/07/22 | 150.00\n"
      StatementGenerator.print(mockLedger) shouldEqual expectedString
    }
  }

}

