package shared.serializers

import java.nio.ByteBuffer

import shared.model._
import shared.protocol._
import boopickle.Default._

object Serializers {
  implicit val dirP = compositePickler[Direction]
    .addConcreteType[Up.type]
    .addConcreteType[Down.type]
    .addConcreteType[Left.type]
    .addConcreteType[Right.type]

  implicit val cmdP = compositePickler[GameCommand]
    .addConcreteType[ChangeDirection]
    .addConcreteType[JoinGame]
    .addConcreteType[SpeedUp.type]
    .addConcreteType[LeaveGame.type]
    .addConcreteType[DebugNextFrame.type]

  implicit val gameRequestP = compositePickler[GameRequest].join(cmdP)

  implicit val gameResponseP = compositePickler[GameResponse]
    .addConcreteType[GameState]
    .addConcreteType[AssignedID]

  def bbToArrayBytes(buffer: ByteBuffer): Array[Byte] = {
    val data = Array.ofDim[Byte](buffer.remaining())
    buffer.get(data)
    data
  }
}
