import scala.collection.mutable

trait StatementBase {
  def print(ledger: scala.collection.mutable.Set[Transaction]): String
}

object StatementGenerator extends StatementBase {
  def print(ledger: scala.collection.mutable.Set[Transaction]): String = {
    "Amount      | Date        | Balance     "
  }
}

