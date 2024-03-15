package com.Group22.lab.service.impl.UserServiceImpl;

import cn.hutool.log.Log;
import com.Group22.lab.common.Constants;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangeEmail;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangePassword;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangePhone;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangeUsername;
import com.Group22.lab.entity.User.UserAccount;
import com.Group22.lab.exception.ServiceException;
import com.Group22.lab.dto.ByUser.Login;
import com.Group22.lab.dto.ByUser.Register;
import com.Group22.lab.entity.User.UserData;
import com.Group22.lab.mapper.UserAccountMapper;
import com.Group22.lab.mapper.UserDataMapper;
import com.Group22.lab.service.IUserService.IUserDataService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HouBinyang
 * @since 2023-03-13
 */
@Service
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper, UserData> implements IUserDataService {
    @Resource
    private UserAccountMapper userAccountMapper;
    private static final Log LOG = Log.get();
    @Override
    public UserData login(Login login) {
        QueryWrapper<UserData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", login.getUsername());
        queryWrapper.eq("password", login.getPassword());
        UserData one = getOne(queryWrapper);
        if (one != null) {
            return one;
        } else {
            throw new ServiceException(Constants.CODE_401, "用户名或密码错误");
        }
    }

    @Override
    public UserData register(Register register) {
        QueryWrapper<UserData> queryWrapper1 = new QueryWrapper<>();
        QueryWrapper<UserData> queryWrapper2 = new QueryWrapper<>();
        QueryWrapper<UserData> queryWrapper3 = new QueryWrapper<>();
        QueryWrapper<UserData> queryWrapper4 = new QueryWrapper<>();
        queryWrapper1.eq("email", register.getEmail());
        UserData one = getOne(queryWrapper1);
        if (one != null) {
            throw new ServiceException(Constants.CODE_402, "该邮箱已被注册过");
        }
        queryWrapper2.eq("phone", register.getPhone());
        UserData two = getOne(queryWrapper2);
        if (two != null) {
            throw new ServiceException(Constants.CODE_402, "该手机号已被注册过");
        }
        queryWrapper3.eq("id_number", register.getId_number());
        UserData three = getOne(queryWrapper3);
        if (three != null) {
            throw new ServiceException(Constants.CODE_402, "该身份证号已被注册过");
        }
        queryWrapper4.eq("username", register.getUsername());
        UserData four = getOne(queryWrapper4);
        if (four != null) {
            throw new ServiceException(Constants.CODE_403, "用户名已存在");
        }
        UserData addUser = new UserData();
        addUser.setEmail(register.getEmail());
        addUser.setPhone(register.getPhone());
        addUser.setIdNumber(register.getId_number());
        addUser.setUsername(register.getUsername());
        addUser.setPassword(register.getPassword());
        addUser.setUserType(register.getUser_type());
        boolean success = save(addUser);
        if (success) {
            QueryWrapper<UserData> getuid = new QueryWrapper<>();
            getuid.eq("username", register.getUsername());
            UserData addAccount = getOne(getuid);
            UserAccount add = new UserAccount(addAccount.getUid(),"正常使用中",0.0,null);
            userAccountMapper.insert(add);
            return addAccount;
        } else {
            throw new ServiceException(Constants.CODE_500, "后端错误，注册失败");
        }
    }

    @Override
    public UserData changeUsername(ChangeUsername changeUsername) {
        QueryWrapper<UserData> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("uid", changeUsername.getUid());
        UserData one = getOne(queryWrapper1);
        if (one != null) {
            QueryWrapper<UserData> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("username", changeUsername.getUsername());
            UserData two = getOne(queryWrapper2);
            if (two != null) {
                throw new ServiceException(Constants.CODE_403, "用户名已存在");
            } else {
                one.setUsername(changeUsername.getUsername());
                saveOrUpdate(one);
                return one;
            }
        } else {
            throw new ServiceException(Constants.CODE_400, "用户不存在");
        }
    }

    @Override
    public UserData changeEmail(ChangeEmail changeEmail) {
        QueryWrapper<UserData> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("uid", changeEmail.getUid());
        UserData one = getOne(queryWrapper1);
        if (one != null) {
            QueryWrapper<UserData> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("email", changeEmail.getEmail());
            UserData two = getOne(queryWrapper2);
            if (two != null) {
                throw new ServiceException(Constants.CODE_402, "该邮箱已注册过一个账号");
            } else {
                one.setEmail(changeEmail.getEmail());
                saveOrUpdate(one);
                return one;
            }
        } else {
            throw new ServiceException(Constants.CODE_400, "用户不存在");
        }
    }

    @Override
    public UserData changePhone(ChangePhone changePhone) {
        QueryWrapper<UserData> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("uid", changePhone.getUid());
        UserData one = getOne(queryWrapper1);
        if (one != null) {
            QueryWrapper<UserData> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("phone", changePhone.getPhone());
            UserData two = getOne(queryWrapper2);
            if (two != null) {
                throw new ServiceException(Constants.CODE_402, "该手机号已注册过一个账号");
            } else {
                one.setPhone(changePhone.getPhone());
                saveOrUpdate(one);
                return one;
            }
        } else {
            throw new ServiceException(Constants.CODE_400, "用户不存在");
        }
    }

    @Override
    public UserData changePassword(ChangePassword changePassword) {
        QueryWrapper<UserData> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("uid", changePassword.getUid());
        UserData one = getOne(queryWrapper1);
        if (one != null) {
            one.setPassword(changePassword.getPassword());
            saveOrUpdate(one);
            return one;
        } else {
            throw new ServiceException(Constants.CODE_400, "用户不存在");
        }
    }


}
