package starmage27.fontpreview.data

import java.io.FileInputStream
import java.nio.ByteBuffer

data class TableRecord(
    val tableTag: CharArray,
    val checksum: ByteArray,
    val offset: Long,
    val length: Int,
) {
    fun isTable(table: FontTable): Boolean {
        return tableTag.contentEquals(table.value.toCharArray())
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TableRecord

        if (offset != other.offset) return false
        if (length != other.length) return false
        if (!tableTag.contentEquals(other.tableTag)) return false
        if (!checksum.contentEquals(other.checksum)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = offset.hashCode()
        result = 31 * result + length.hashCode()
        result = 31 * result + tableTag.contentHashCode()
        result = 31 * result + checksum.contentHashCode()
        return result
    }
}

fun FileInputStream.readTableRecord(): TableRecord {
    val bytes = ByteArray(16)
    val byteBuffer = ByteBuffer.wrap(bytes)
    this.read(bytes)

    return byteBuffer.getTableRecord()
}

fun ByteBuffer.getTableRecord(): TableRecord {
    val tableTag = CharArray(4)
    for (i in 0 until 4) {
        tableTag[i] = this.get().toInt().toChar()
    }
    val checksum = ByteArray(4)
    for (i in 0 until 4) {
        checksum[i] = this.get()
    }
    val offset = this.getInt().toUInt().toLong()
    val length = this.getInt()

    return TableRecord(
        tableTag = tableTag,
        checksum = checksum,
        offset = offset,
        length = length
    )
}