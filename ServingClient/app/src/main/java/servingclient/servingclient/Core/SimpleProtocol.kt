package servingclient.servingclient.Core


class SimpleProtocol: Protocol {
    override fun decode(byteArray: ByteArray): String {
        val inputString = String(byteArray)
        return inputString
    }

    override fun encode(string: String): ByteArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}