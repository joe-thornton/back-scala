import scala.collection.mutable.ArrayBuffer
import com.github.nscala_time.time.Imports._

trait StatementBase {
  def print(ledger: ArrayBuffer[Transaction]): String
}

object StatementGenerator extends StatementBase {
  def print(ledger: ArrayBuffer[Transaction]): String = {
    val header: String = "Amount | Date | Balance\n"
    val transactionLines: String = printLedger(ledger)
    val text = header.concat(transactionLines)
    text
  }

  private[this] def printLedger(ledger: ArrayBuffer[Transaction]): String = {
    if (ledger.isEmpty) ""
    else ledgerWithRunningBalance(ledger)
  }

  private[this] def ledgerWithRunningBalance(ledger: ArrayBuffer[Transaction]): String = {
    var balance: Double = 0
    ledger.sortBy(_.dateAndTime).map(transaction => {
      balance += transaction.amount
      f"${transaction.amount}%.2f | ${dateTimeString(transaction.dateAndTime)} | ${balance}%.2f\n"
    }).mkString("")
  }

  private[this] def dateTimeString(dateAndTime: LocalDateTime): String = {
    dateAndTime.toString("dd/MM/yy")
  }
}

