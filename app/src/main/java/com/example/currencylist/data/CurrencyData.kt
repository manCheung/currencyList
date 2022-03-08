package com.example.currencylist.data

import com.example.currencylist.model.CurrencyModel
import com.google.gson.Gson

object CurrencyData {
    fun getInitCurrencyData(): List<CurrencyModel> {
        val dataStr = "[\n" +
                "   {\n" +
                "      \"id\":\"BTC\",\n" +
                "      \"name\":\"Bitcoin\",\n" +
                "      \"symbol\":\"BTC\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"ETH\",\n" +
                "      \"name\":\"Ethereum\",\n" +
                "      \"symbol\":\"ETH\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"XRP\",\n" +
                "      \"name\":\"XRP\",\n" +
                "      \"symbol\":\"XRP\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"BCH\",\n" +
                "      \"name\":\"Bitcoin Cash\",\n" +
                "      \"symbol\":\"BCH\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"LTC\",\n" +
                "      \"name\":\"Litecoin\",\n" +
                "      \"symbol\":\"LTC\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"EOS\",\n" +
                "      \"name\":\"EOS\",\n" +
                "      \"symbol\":\"EOS\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"BNB\",\n" +
                "      \"name\":\"Binance Coin\",\n" +
                "      \"symbol\":\"BNB\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"LINK\",\n" +
                "      \"name\":\"Chainlink\",\n" +
                "      \"symbol\":\"LINK\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"NEO\",\n" +
                "      \"name\":\"NEO\",\n" +
                "      \"symbol\":\"NEO\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"ETC\",\n" +
                "      \"name\":\"Ethereum Classic\",\n" +
                "      \"symbol\":\"ETC\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"ONT\",\n" +
                "      \"name\":\"Ontology\",\n" +
                "      \"symbol\":\"ONT\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"CRO\",\n" +
                "      \"name\":\"Crypto.com Chain\",\n" +
                "      \"symbol\":\"CRO\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"CUC\",\n" +
                "      \"name\":\"Cucumber\",\n" +
                "      \"symbol\":\"CUC\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":\"USDC\",\n" +
                "      \"name\":\"USD Coin\",\n" +
                "      \"symbol\":\"USDC\"\n" +
                "   }\n" +
                "]"

        return Gson().fromJson(dataStr , Array<CurrencyModel>::class.java).toList()

    }
}
