package servingclient.servingclient.Core


interface Protocol {
    public fun decode(byteArray: ByteArray): String

    public fun encode(string: String): ByteArray
}