package org.ergoplatform.explorer.http.api.v0.models

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import sttp.tapir.Schema
import sttp.tapir.generic.Derived

final case class BlockChainInfo(
  version: String,
  supply: Long,
  transactionAverage: Int, // avg. number of transactions per block.
  hashRate: Long
)

object BlockChainInfo {

  implicit val codec: Codec[BlockChainInfo] = deriveCodec

  implicit val schema: Schema[BlockChainInfo] =
    implicitly[Derived[Schema[BlockChainInfo]]].value
      .modify(_.version)(_.description("Network protocol version"))
      .modify(_.supply)(_.description("Total supply in nanoErgs"))
      .modify(_.transactionAverage)(_.description("Average number of transactions per block"))
      .modify(_.hashRate)(_.description("Network hash rate"))

  def empty: BlockChainInfo = BlockChainInfo("0.0.0", 0L, 0, 0L)
}
