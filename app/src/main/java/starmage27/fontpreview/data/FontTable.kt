package starmage27.fontpreview.data

@Suppress("SpellCheckingInspection")
enum class FontTable(val value: String) {
    // Required Tables
    CharacterMap    ("cmap"),
    FontHeader      ("head"),
    HorizontalHeader("hhea"),
    HorizontalMetics("hmtx"),
    MaximumProfile  ("maxp"),
    NamingTable     ("name"),
    WindowsMetrics  ("OS/2"),
    PostscriptInfo  ("post"),

    // Tables Related to TrueType Outlines
    ControlValue       ("cvt "), // Optional
    FontProgram        ("fpgm"), // Optional
    GlyphData          ("glyf"),
    IndexToLocation    ("loca"),
    ControlValueProgram("prep"), // Optional
    ScanConversion     ("gasp"), // Optional

    // Tables Related to CFF Outlines
    CompactFonttFormat1("CFF "),
    CompactFonttFormat2("CFF2"),
    VerticalOrigin     ("VORG"), // Optional

    // Table Related to SVG Outlines
    SVG("SVG "),

    // Tables Related to Bitmap Glyphs
    EmbeddedBitmap        ("EBDT"),
    EmbeddedBitmapLocation("EBLC"),
    EmbeddedBitmapScaling ("EBSC"),
    ColorBitmap           ("CBDT"),
    ColorBitmapLocation   ("CBLC"),
    StandardBitmapGraphics("sbix"),

    // Advanced Typographic Tables
    Baseline         ("BASE"),
    GlyphDefinition  ("GDEF"),
    GlyphPositioning ("GPOS"),
    GlyphSubstitution("GSUB"),
    Justification    ("JSTF"),
    MathLayout       ("MATH"),

    // Tables used for OpenType Font Variations
    AxisVariations             ("avar"),
    CVTVariations              ("cvar"), // TrueType outlines only
    FontVariations             ("fvar"),
    GlyphVariations            ("gvar"), // TrueType outlines only
    HorizontalMetricsVariations("HVAR"),
    MetricsVariations          ("MVAR"),
    StyleAttributes            ("STAT"), // required for variable fonts, optional for non-variable fonts
    VerticalMetricsVariations  ("VVAR"),

    // Tables Related to Color Fonts
    Color                 ("COLR"),
    ColorPalette          ("CPAL"),
//  ColorBitmap           ("CBDT"),
//  ColorBitmapLocation   ("CBLC"),
//  StandardBitmapGraphics("sbix"),
//  SVG                   ("SVG "),

    // Other OpenType Tables
    DigitalSignature       ("DSIG"),
    HorizontalDeviceMetrics("hdmx"),
    Kerning                ("kern"),
    LinearThreshold        ("LTSH"),
    Merge                  ("MERG"),
    Metadata               ("meta"),
//  StyleAttributes        ("STAT"),
    PCL5                   ("PCLT"),
    VerticalDeviceMetrics  ("VDMX"),
    VerticalMetricsHeader  ("vhea"),
    VerticalMetrics        ("vmtx"),
}