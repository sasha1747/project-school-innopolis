package com.company;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 15.08.18.
 */
public class Deployer {
    public static void main(String[] args) throws Exception {
        Web3j web3 = Web3j.build(new HttpService("https://kovan.infura.io/mew"));


        String contractAddr="0x23b79cc0a7eaecb6b901f1777093af52709c6baa";
        String privkey="8e1a2ec8d8e729183e028207c068ec9a4655512017e456b301a18d5bbc350775";
        String pubkey="";

        BigInteger privateKeyInBT = new BigInteger(privkey, 16);

        ECKeyPair aPair = ECKeyPair.create(privateKeyInBT);

        BigInteger publicKeyInBT = aPair.getPublicKey();

        String sPublickeyInHex = publicKeyInBT.toString(16);
        pubkey=sPublickeyInHex;




        Credentials credentials = Credentials.create(privkey,pubkey);//WalletUtils.loadCredentials("cfvggkrubhcg","MezhBlockchain/keyMetamask.json");

        Sm1 sm1=Sm1.deploy(web3,credentials,big(1),big(200000)).send();
        System.out.println("sm1:    "+sm1.getContractAddress());
        List<String> list=new ArrayList<>();
        list.add("0x7B4B4D811cb805F936854BF9Ea30E6B3b69dDB11");
        list.add("0x570921b1bc7d749f13a162fb5f1c89e0aaa7aba8");
        list.add("0xe868b537d601a40244bca72f7a7bf71ecb966dda");
        Sm2 sm2=Sm2.deploy(web3,credentials,big(1),big(2000000),list).send();
        System.out.println("sm2:    "+sm2.getContractAddress());
        String sm1addr="0x3f51400facd6f4e2d04b9d4c9f0ce0012fef4b0f";
        String sm2addr="0xafd6f3dd885ee964365f891d91733133b6c93017";

    }

    private static BigInteger big(int i) {
        return new BigInteger(Integer.toString(i));
    }
}
