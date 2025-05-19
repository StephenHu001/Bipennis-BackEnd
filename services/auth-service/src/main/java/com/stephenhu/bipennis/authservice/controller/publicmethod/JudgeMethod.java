package com.stephenhu.bipennis.authservice.controller.publicmethod;

import com.stephenhu.bipennis.authservice.dao.impl.SpareEmailCrudRepositoryImpl;
import com.stephenhu.bipennis.model.VO.authservice.LoginPageVO;
import com.stephenhu.bipennis.model.VO.authservice.RegisterPageVO;
import com.stephenhu.bipennis.util.Regular.DateRegular;
import com.stephenhu.bipennis.util.Regular.EmailRegular;
import com.stephenhu.bipennis.util.Regular.PasswordRegular;
import com.stephenhu.bipennis.util.Regular.PhoneRegular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Stephen Hu
 */
@Component
public final class JudgeMethod {
    private static final Logger logger = LoggerFactory.getLogger(SpareEmailCrudRepositoryImpl.class);
    public boolean isRightRegisterPageVO(RegisterPageVO registerPageVO){
        if (registerPageVO.getUPhone() == null ||
                registerPageVO.getUPhone().trim().isEmpty() ||
                registerPageVO.getLastName() == null ||
                registerPageVO.getLastName().trim().isEmpty() ||
                registerPageVO.getHashPassword() == null ||
                registerPageVO.getHashPassword().trim().isEmpty() ||
                registerPageVO.getBirthdate() == null ||
                registerPageVO.getBirthdate().trim().isEmpty() ||
                registerPageVO.getUEmail() == null||
                registerPageVO.getUEmail().trim().isEmpty()) {
            // 日志记录器
            logger.warn("Invalid Register parameter");
            return false;
        }

        if (!EmailRegular.isEmail(registerPageVO.getUEmail())) {
            logger.warn("Invalid email parameter: {}", registerPageVO.getUEmail());
            return false;
        }

        if (!PhoneRegular.isInternationalPhone(registerPageVO.getUPhone())) {
            logger.warn("Invalid phone parameter: {}", registerPageVO.getUPhone());
            return false;
        }

        if (!DateRegular.isValidDate(registerPageVO.getBirthdate())){
            logger.warn("Invalid birthdate parameter: {}", registerPageVO.getBirthdate());
            return false;
        }

        if (!PasswordRegular.isValidPassword(registerPageVO.getHashPassword())){
            logger.warn("Invalid password parameter: {}", registerPageVO.getHashPassword());
            return false;
        }
        return true;
    }

    public boolean isRightLoginPageVO(LoginPageVO loginPageVO){
        if (loginPageVO.getAccount() == null || loginPageVO.getOriginPassword() == null) {
            // 日志记录器
            logger.warn("Invalid LoginVO parameter");
            return false;
        }
        return true;
    }
}
