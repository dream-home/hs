package com.service;

import com.base.service.CommonService;
import com.constant.WalletOrderStatus;
import com.constant.WalletOrderType;
import com.model.SysUser;
import com.model.WalletTransaction;
import ru.paradoxs.bitcoin.client.BitcoinClient;

import java.util.List;

/**
 * @date 2016年11月27日
 */
public interface WalletTransactionService extends CommonService<WalletTransaction> {

    public Integer addWalletOrderTransaction(String userId, String address, WalletOrderType walletOrderType, WalletOrderStatus walletOrderStatus, String txtId, String orderNo, Double amount);

    public Boolean createTransaction(String userId, Integer currencyType, BitcoinClient client);

    public List<WalletTransaction> listByStartToEnd(Long start, Long end);

    public List<WalletTransaction> listByTransactionTime(Long start, Long end, Integer account);

    public void listTransactionsByTag(String userId, Integer currencyType) throws Exception;

    public void checkTransactionListStatus(String userId, Integer currencyType) throws Exception;

    public void checkTransactionStatus(WalletTransaction transaction) throws Exception;

    public Boolean checkCoinOut(SysUser loginUser, String orderId,Integer status)  throws Exception;




}
