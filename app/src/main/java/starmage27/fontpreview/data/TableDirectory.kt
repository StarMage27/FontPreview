package starmage27.fontpreview.data

import java.io.FileInputStream
import java.nio.ByteBuffer

data class TableDirectory(
    val sfntVersion: Int,
    val numTables: Int,
    val searchRange: Int,
    val entrySelector: Int,
    val rangeShift: Int,
    val tableRecords: MutableList<TableRecord> = mutableListOf(),
) {
    fun isValid(): Boolean {
        return (sfntVersion == 0x00010000) || (sfntVersion == 0x4F54544F)
    }
}

fun FileInputStream.readTableDirectory(): TableDirectory {
    val bytes = ByteArray(12)
    val byteBuffer = ByteBuffer.wrap(bytes)
    this.read(bytes)

    val tableDirectory = byteBuffer.getTableDirectory()

    repeat(tableDirectory.numTables) {
        tableDirectory.tableRecords.add(this.readTableRecord())
    }

    return tableDirectory
}

fun ByteBuffer.getTableDirectory(): TableDirectory {
    val sfntVersion   = this.getInt()
    val numTables     = this.getShort().toUShort().toInt()
    val searchRange   = this.getShort().toUShort().toInt()
    val entrySelector = this.getShort().toUShort().toInt()
    val rangeShift    = this.getShort().toUShort().toInt()

    return TableDirectory(
        sfntVersion = sfntVersion,
        numTables = numTables,
        searchRange = searchRange,
        entrySelector = entrySelector,
        rangeShift = rangeShift,
    )
}