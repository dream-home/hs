package com.service.impl;

import com.base.dao.CommonDao;
import com.base.service.impl.CommonServiceImpl;
import com.constant.CodeType;
import com.constant.Constant;
import com.mapper.ActiveCodeMapper;
import com.model.User;
import com.service.ActiveCodeService;
import com.service.TransferCodeService;
import com.service.UserService;
import com.model.ActiveCode;
import com.model.TransferCode;
import com.utils.toolutils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date 2017年08月17日
 */
@Service
public class ActiveCodeServiceImpl extends CommonServiceImpl<ActiveCode> implements ActiveCodeService {
    @Autowired
    private ActiveCodeMapper activeCodeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private TransferCodeService transferCodeService;

    @Override
    protected CommonDao<ActiveCode> getDao() {
        return activeCodeMapper;
    }

    @Override
    protected Class<ActiveCode> getModelClass() {
        return ActiveCode.class;
    }

    @Override
    @Transactional
    public Boolean codeTransfer(String fromUserId, String toUserId, Integer toUid, Integer transferNo,Integer codeType) {
        if (codeType==null || codeType==0){
            return false;
        }
        User from=userService.readById(fromUserId);
        User to=userService.readById(toUserId);
        TransferCode transferCode = new TransferCode();
        if (codeType==CodeType.ACTIVATECODE.getCode()){
            userService.updateUserActiveCode(fromUserId, -transferNo);
            userService.updateUserActiveCode(toUserId, transferNo);
            transferCode.setType(CodeType.TRANSFER_USE_ACTIVECODE.getCode());
            transferCode.setRemark("用户转让消费码：" + from.getUid() + "转让" + to.getUid()+ " "+transferNo+"个");
        }else if (codeType==CodeType.REGISTERCODE.getCode()){
            userService.updateUserRegisterCode(fromUserId, -transferNo);
            userService.updateUserRegisterCode(toUserId, transferNo);
            transferCode.setType(CodeType.TRANSFER_USE_REGISTERCODE.getCode());
            transferCode.setRemark("用户转让注册码：" + from.getUid() + "转让" + to.getUid()+ " "+transferNo+"个");
        }
        transferCode.setSendUserId(fromUserId);
        transferCode.setReceviceUserId(toUserId);
        transferCode.setTransferNo(-transferNo);
        transferCodeService.create(transferCode);
        return true;
    }

    @Override
    @Transactional
    public Boolean useActiveCode(String userId, Integer activeNo, String remark) {
        userService.updateUserActiveCode(userId, -activeNo);
        TransferCode transferCode = new TransferCode();
        transferCode.setSendUserId(userId);
        transferCode.setReceviceUserId(Constant.SYSTEM_USER_ID);
        transferCode.setType(CodeType.REGISTER_USE_ACTIVECODE.getCode());
        transferCode.setTransferNo(-activeNo);
        if (ToolUtil.isEmpty(remark)) {
            transferCode.setRemark("用户激活账号,使用"+activeNo+"个消费码");
        } else {
            transferCode.setRemark(remark);
        }
        transferCodeService.create(transferCode);
        return true;
    }

    @Override
    public Boolean useRegisterCode(String userId, Integer registerCodeNo, String remark) {
        userService.updateUserRegisterCode(userId,-registerCodeNo);
        TransferCode transferCode = new TransferCode();
        transferCode.setSendUserId(userId);
        transferCode.setReceviceUserId(Constant.SYSTEM_USER_ID);
        transferCode.setType(CodeType.REGISTER_USE_REGISTERCODE.getCode());
        transferCode.setTransferNo(-registerCodeNo);
        if (ToolUtil.isEmpty(remark)) {
            transferCode.setRemark("用户注册账号,使用"+registerCodeNo+"个消费码");
        } else {
            transferCode.setRemark(remark);
        }
        transferCodeService.create(transferCode);
        return true;
    }
}
