package starmage27.fontpreview.data

import androidx.compose.runtime.Immutable
import kotlin.String

@Immutable
data class FontInfo(
    val copyrightNotice: String, // 0
    val fontFamilyName: String, // 1
    val fontSubfamilyName: String, // 2
    val uniqueFontIdentifier: String, // 3
    val fullFontName: String, // 4
    val version: String, // 5
    val postscriptName: String, // 6
    val trademark: String, // 7
    val manufacturerName: String, // 8
    val designer: String, // 9
    val description: String, // 10
    val vendorURL: String, // 11
    val designerURL: String, // 12
    val licenseDescription: String, // 13
    val licenseInfoURL: String, // 14
    val reserved: String, // 15
    val typographicFamilyName: String, // 16
    val typographicSubfamilyName: String, // 17
    val compatibleFullName: String, // 18
    val sampleText: String, // 19
    val postscriptCIDFindFontName: String, // 20
    val familyNameWWS: String, // 21
    val familySubnameWWS: String, // 22
    val lightBackgroundPalette: String, // 23
    val darkBackgroundPalette: String, // 24
    val variationsPostScriptNamePrefix: String, // 25
) {
    companion object {
        val exampleFontInfo = FontInfo(
            "© Copyright the Monotype Corporation plc, 1990",
            "Times New Roman",
            "Bold",
            "Times New Roman Bold: 1990",
            "Times New Roman Bold",
            "Version 1.00 June 1, 1990, initial release",
            "TimesNewRoman-Bold",
            "Times New Roman is a registered trademark of the Monotype Corporation.\n",
            "Monotype Corporation",
            "Stanley Morison",
            "Designed in 1932 for the Times of London newspaper. Excellent readability and a narrow overall width, allowing more words per line than most fonts.",
            "http://www.monotype.com",
            "http://www.monotype.com",
            "This font may be installed on all of your machines and printers, but you may not sell or give these fonts to anyone else.",
            "http://www.monotype.com/license/",
            "RESERVED",
            "",
            "",
            "",
            "The quick brown fox jumps over the lazy dog.",
            "",
            "",
            "",
            "",
            "",
            "",
        )
    }
}

data class MutableFontInfo(
    var copyrightNotice: String = "", // 0
    var fontFamilyName: String = "", // 1
    var fontSubFamilyName: String = "", // 2
    var uniqueFontIdentifier: String = "", // 3
    var fullFontName: String = "", // 4
    var version: String = "", // 5
    var postscriptName: String = "", // 6
    var trademark: String = "", // 7
    var manufacturerName: String = "", // 8
    var designer: String = "", // 9
    var description: String = "", // 10
    var vendorURL: String = "", // 11
    var designerURL: String = "", // 12
    var licenseDescription: String = "", // 13
    var licenseInfoURL: String = "", // 14
    var reserved: String = "", // 15
    var typographicFamilyName: String = "", // 16
    var typographicSubfamilyName: String = "", // 17
    var compatibleFullName: String = "", // 18
    var sampleText: String = "", // 19
    var postscriptCIDFindFontName: String = "", // 20
    var familyNameWWS: String = "", // 21
    var familySubnameWWS: String = "", // 22
    var lightBackgroundPalette: String = "", // 23
    var darkBackgroundPalette: String = "", // 24
    var variationsPostScriptNamePrefix: String = "", // 25
) {
    fun getImmutable(): FontInfo {
        return FontInfo(
            copyrightNotice,
            fontFamilyName,
            fontSubFamilyName,
            uniqueFontIdentifier,
            fullFontName,
            version,
            postscriptName,
            trademark,
            manufacturerName,
            designer,
            description,
            vendorURL,
            designerURL,
            licenseDescription,
            licenseInfoURL,
            reserved,
            typographicFamilyName,
            typographicSubfamilyName,
            compatibleFullName,
            sampleText,
            postscriptCIDFindFontName,
            familyNameWWS,
            familySubnameWWS,
            lightBackgroundPalette,
            darkBackgroundPalette,
            variationsPostScriptNamePrefix,
        )
    }

    fun setInfo(nameID: Int, info: String) {
        when (nameID) {
             0 -> this.copyrightNotice = info
             1 -> this.fontFamilyName = info
             2 -> this.fontSubFamilyName = info
             3 -> this.uniqueFontIdentifier = info
             4 -> this.fullFontName = info
             5 -> this.version = info
             6 -> this.postscriptName = info
             7 -> this.trademark = info
             8 -> this.manufacturerName = info
             9 -> this.designer = info
            10 -> this.description = info
            11 -> this.vendorURL = info
            12 -> this.designerURL = info
            13 -> this.licenseDescription = info
            14 -> this.licenseInfoURL = info
            15 -> this.reserved = info
            16 -> this.typographicFamilyName = info
            17 -> this.typographicSubfamilyName = info
            18 -> this.compatibleFullName = info
            19 -> this.sampleText = info
            20 -> this.postscriptCIDFindFontName = info
            21 -> this.familyNameWWS = info
            22 -> this.familySubnameWWS = info
            23 -> this.lightBackgroundPalette = info
            24 -> this.darkBackgroundPalette = info
            25 -> this.variationsPostScriptNamePrefix = info
        }
    }
}