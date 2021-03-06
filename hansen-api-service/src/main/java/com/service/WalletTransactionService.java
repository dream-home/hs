package com.service;

import com.base.service.CommonService;
import com.constant.WalletOrderType;
import com.model.User;
import com.model.WalletTransaction;
import ru.paradoxs.bitcoin.client.BitcoinClient;

import java.util.List;

/**
 * @date 2016年11月27日
 */
public interface WalletTransactionService extends CommonService<WalletTransaction> {

    public Integer addWalletOrderTransaction(String userId, String address, WalletOrderType walletOrderType, String txtId, String orderNo, Double amount);

    public Boolean createTransaction(String uid, Integer currencyType, BitcoinClient client);

    public List<WalletTransaction> listByStartToEnd(Long start, Long end);

    public List<WalletTransaction> listByTransactionTime(Long start, Long end, Integer account);

    public void listTransactionsByTag(String userId, Integer currencyType) throws Exception;

    public void checkTransactionListStatus(String userId, Integer currencyType) throws Exception;

    public void checkTransactionStatus(WalletTransaction transaction) throws Exception;

    List<WalletTransaction> readCoinOutterListByUid(String uid,List<Integer> list) throws Exception;

    Integer readCoinOutterCountByUid(String uid,List<Integer> list) ;

    List<WalletTransaction> handleWalletTransactions(User user, List<Integer> orderTypeList, List<WalletTransaction> transactionList);
}
