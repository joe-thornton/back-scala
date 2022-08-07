import scala.collection.mutable
import com.github.nscala_time.time.Imports._

trait StatementBase {
  def print(ledger: scala.collection.mutable.Set[Transaction]): String
}

object StatementGenerator extends StatementBase {
  def print(ledger: scala.collection.mutable.Set[Transaction]): String = {
    val header: String = "Amount, Date, Balance\n"
    val transactionLines: String = printLedger(ledger)
    val text = header.concat(transactionLines)
    text
  }

  private[this] def printLedger(ledger: scala.collection.mutable.Set[Transaction]): String = {
    if (ledger.isEmpty) ""
    else f"${ledger.head.amount}%.2f | ${dateTimeString(ledger.head.dateAndTime)} | ${ledger.head.amount}%.2f\n"
  }

  private[this] def dateTimeString(dateAndTime: LocalDateTime): String = {
    dateAndTime.toString("dd/MM/yy")
  }
}

