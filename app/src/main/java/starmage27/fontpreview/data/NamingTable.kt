package starmage27.fontpreview.data

import java.io.FileInputStream
import java.nio.ByteBuffer

data class NamingTable(
    val version: Int,
    val count: Int,
    val storageOffset: Int,
    val nameRecords: MutableList<NameRecord> = mutableListOf(),
)

fun FileInputStream.readNamingTable(): NamingTable {
    val bytes = ByteArray(12)
    val byteBuffer = ByteBuffer.wrap(bytes)
    this.read(bytes)

    val namingTable = byteBuffer.getNamingTable()

    repeat(namingTable.count) {
        namingTable.nameRecords.add(this.readNameRecord())
    }

    return namingTable
}

fun ByteBuffer.getNamingTable(): NamingTable {
    val version = this.getShort().toUShort().toInt() // idc about version 1
    val count   = this.getShort().toUShort().toInt()
    val offset  = this.getShort().toUShort().toInt()

    return NamingTable(
        version = version,
        count = count,
        storageOffset = offset
    )
}