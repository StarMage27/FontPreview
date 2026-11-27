package starmage27.fontpreview.data

import java.io.FileInputStream
import java.nio.ByteBuffer

data class NameRecord(
    val platformID: Int,
    val encodingID: Int,
    val languageID: Int,
    val nameID: Int,
    val length: Int,
    val offset: Int,
) {
}

fun FileInputStream.readNameRecord(): NameRecord {
    val bytes = ByteArray(12)
    val byteBuffer = ByteBuffer.wrap(bytes)
    this.read(bytes)

    return byteBuffer.getNameRecord()
}

fun ByteBuffer.getNameRecord(): NameRecord {
    val platformID = this.getShort().toUShort().toInt()
    val encodingID = this.getShort().toUShort().toInt()
    val languageID = this.getShort().toUShort().toInt()
    val nameID     = this.getShort().toUShort().toInt()
    val length     = this.getShort().toUShort().toInt()
    val offset     = this.getShort().toUShort().toInt()

    return NameRecord(
        platformID = platformID,
        encodingID = encodingID,
        languageID = languageID,
        nameID = nameID,
        length = length,
        offset = offset,
    )
}