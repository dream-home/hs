package com.mapper;

import com.base.dao.CommonDao;
import com.model.WalletTransaction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletTransactionMapper extends CommonDao<WalletTransaction> {

    public List<WalletTransaction> listByTransactionTime(@Param("start") Long start, @Param("end") Long end, @Param("userId") Integer userId);

    public List<WalletTransaction> listByStartToEnd(@Param("start") Long start, @Param("end") Long end);

    public Boolean updateTransactionStatusByTxId(@Param("txId")String txId, @Param("confirmations")Integer confirmations,@Param("transactionStatus") String transactionStatus);

    List<WalletTransaction> readCoinOutterListByUid(@Param("uid") String uid ,@Param("list") List<Integer> list);

    Integer  readCoinOutterCountByUid  (@Param("uid") String uid,@Param("list") List<Integer> list);
}
