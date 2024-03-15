package com.Group22.lab.service.IUserService;

import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangeEmail;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangePassword;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangePhone;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangeUsername;
import com.Group22.lab.dto.ByUser.Login;
import com.Group22.lab.dto.ByUser.Register;
import com.Group22.lab.entity.User.UserData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-03-13
 */
public interface IUserDataService extends IService<UserData> {

    UserData login(Login login);

    UserData register(Register register);

    UserData changeUsername(ChangeUsername changeUsername);

    UserData changeEmail(ChangeEmail changeEmail);

    UserData changePhone(ChangePhone changePhone);

    UserData changePassword(ChangePassword changePassword);

}
