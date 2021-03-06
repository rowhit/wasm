package wasm.text

import scala.util.parsing.input.{NoPosition, Position, Reader}
import wasm.ast.Token

class TokenReader(tokens: Seq[Token]) extends Reader[Token] {
  override def first: Token = tokens.head
  override def atEnd: Boolean = tokens.isEmpty
  override def pos: Position = tokens.headOption.map(_.pos).getOrElse(NoPosition)
  override def rest: Reader[Token] = new TokenReader(tokens.tail)
}
