package edofro.freeplane.unicodeprogressbar

// import org.freeplane.plugin.script.FreeplaneScriptBaseClass
// import org.freeplane.plugin.script.FreeplaneScriptBaseClass.ConfigProperties


class PBar {

//region: properties

    // static final String attributeForExtensions =  new ConfigProperties().getProperty('groovyConsole_attributeForExtensions','file_ext')
    static final String attributeForExtensions =  'file_ext'
//end:

//region: groovy Node

//end:

//region: 

    public static String  progressBar(String s, boolean withFinalText = true){
        def q = s.isNumber()?s.isInteger()?s.toInteger():s.toDouble():null
        def tot
        if(!q){
            (q,tot) = s.endsWith('%')?([s.dropRight(1).trim().toBigDecimal(),100f]):([null,1])
            if(q)progressBar(q, tot, withFinalText) else '>-- progress bar with no data! --<'
        } else {
            progressBar(q, withFinalText)
        }
    }

    public static String  progressBar(int q, boolean withFinalText = true){
        BigDecimal total = 100
        //def emptySpace = " " //Unicode Character “ ” (U+2002) En Space
        //def finalText =  withFinalText?"${def a = Math.round(q/total*100).toString(); emptySpace *(4 - a.size()) + a}%":GString.EMPTY
        progressBar(q.toBigDecimal(), total, withFinalText)
    }

    public static String  progressBar(BigDecimal q, boolean withFinalText = true){
        BigDecimal total = (q>=1)?100:1
        //def emptySpace = " " //Unicode Character “ ” (U+2002) En Space
        //def finalText =  "${def a = Math.round(q/total*100).toString(); emptySpace *(4 - a.size()) + a}%"
        progressBar(q, total, withFinalText)
    }

    public static String  progressBar(int q, int total, boolean withFinalText = true){
        def emptySpace = " " //Unicode Character “ ” (U+2002) En Space
        def finalText =   withFinalText?"${def a = "${q}/${total}";def l = total.toString().size()*2+2; emptySpace*(Math.max(0,(l - a.size()))) + a}":GString.EMPTY
        privProgressBar(q, total, finalText)
    }

    public static String progressBar(BigDecimal q, BigDecimal total, boolean withFinalText = true){
        def emptySpace = " " //Unicode Character “ ” (U+2002) En Space
        def finalText =  withFinalText?"${def a = Math.round(q/total*100).toString(); emptySpace *(4 - a.size()) + a}%":GString.EMPTY
        //def finalText =  "${def a = "${q}/${total}"; emptySpace*(Math.max(0,(9 - a.size()))) + a}"
        privProgressBar(q, total, finalText)
    }

    public static String  progressBar(int q, BigDecimal total, boolean withFinalText = true){
        progressBar(q.toBigDecimal(), total, withFinalText)
    }

    public static String progressBar(BigDecimal q, int total, boolean withFinalText = true){
        //progressBar(q, total.toBigDecimal())
        progressBar(q.toInteger(), total, withFinalText)
    }

    def private static  privProgressBar(q, total, GString finalText){
        def totalBlocks = 20 // number of blocks that represents 100%
        def emptyBlock = "　" //Unicode Character “　” (U+3000) Ideographic Space
        //def emptyBlock = " " //Unicode Character “ ” (U+2001) Em Quad
        //def emptyBlock = " " //Unicode Character “ ” (U+2007) Figure Space
        //def emptyBlock = " " //Unicode Character “ ” (U+2003) Em Space
        //def emptyBlock = " " //Unicode Character “ ” (U+2002) En Space
        def emptySpace = " " //Unicode Character “ ” (U+2002) En Space
        def openingText = "I" //https://de.wikipedia.org/wiki/Unicodeblock_Geometrische_Formen
        def closingText = "I"

        privProgressBar(q, total, totalBlocks, emptyBlock, openingText, closingText, finalText)
    }

    def private static  privProgressBar(q, total, totalBlocks, emptyBlock, openingText, closingText, finaltext ){
        def av = q/total
        def strdWith = 8

        def fullBlock = "▉" //https://de.wikipedia.org/wiki/Unicodeblock_Blockelemente
        def negativeFullBlock = "░"  // ░▒▓▃
        def blocks     = ["", "▏", "▎", "▍", "▌", "▋", "▊", "▉"]
        def leftBlocks = ["▕","▕","▕","▕","▐","▐", "▐", "▐"]

        def width = totalBlocks * av
        def index = Math.floor(Math.abs(width)%1*8)
        def fullBlockBar = fullBlock * Math.floor(Math.abs(width))
        def rightFullBlockBar = av>1?fullBlock * (Math.floor(Math.abs(width)-totalBlocks)):""
        def bar =  av>=0?(av<=1?fullBlockBar: fullBlock*totalBlocks +closingText+rightFullBlockBar) + blocks[index]: leftBlocks[index] + negativeFullBlock * Math.floor(Math.abs(width))
        def leftText = av>=0? openingText + bar : bar  + openingText
        def texto = leftText + emptyBlock * Math.max(0,(totalBlocks - Math.max(0,(Math.floor(width) + (index>0?1:0)))))  + (av<=1?closingText:"") + finaltext

        /*
        println ""
        println progress
        println width
        println Math.floor(width)
        println Math.floor(width%1*8)
        println totalBlocks - Math.floor(width )
        println ""
        */

        return texto.toString()
    }

//end:

}