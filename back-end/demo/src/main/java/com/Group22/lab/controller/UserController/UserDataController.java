package com.Group22.lab.controller.UserController;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.Group22.lab.common.Constants;
import com.Group22.lab.common.Result;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangeEmail;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangePassword;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangePhone;
import com.Group22.lab.dto.ByUser.ChangeUserInformation.ChangeUsername;
import com.Group22.lab.dto.ByUser.Login;
import com.Group22.lab.dto.ByUser.Register;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.math.BigInteger;

import com.Group22.lab.service.IUserService.IUserDataService;
import com.Group22.lab.entity.User.UserData;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HouBinyang
 * @since 2023-03-13
 */
@RestController
@RequestMapping("/user-data")
public class UserDataController {

    @Resource
    private IUserDataService userDataService;
    @Value("${file.upload-path}")
    private String avatar_url;

    @PostMapping("/login")
    public Result login(@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "用户名和密码不能为空");
        }
        UserData userdata = userDataService.login(login);
        return Result.success(userdata);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Register register) {
        UserData userdata = userDataService.register(register);
        return Result.success(userdata);
    }

    @PostMapping("/change_username")
    public Result changeUsername(@RequestBody ChangeUsername changeUsername) {
        UserData userdata = userDataService.changeUsername(changeUsername);
        return Result.success(userdata);
    }

    @PostMapping("/change_email")
    public Result changeEmail(@RequestBody ChangeEmail changeEmail) {
        UserData userdata = userDataService.changeEmail(changeEmail);
        return Result.success(userdata);
    }

    @PostMapping("/change_phone")
    public Result changePhone(@RequestBody ChangePhone changePhone) {
        UserData userdata = userDataService.changePhone(changePhone);
        return Result.success(userdata);
    }

    @PostMapping("/change_password")
    public Result changePassword(@RequestBody ChangePassword changePassword) {
        UserData userdata = userDataService.changePassword(changePassword);
        return Result.success(userdata);
    }

    @PostMapping
    public Boolean save(@RequestBody UserData userData) {
        return userDataService.saveOrUpdate(userData);
    }

    @DeleteMapping("/delete_{id}")
    public Boolean delete(@PathVariable BigInteger id) {
        return userDataService.removeById(id);
    }

    @GetMapping("/find_all_user_data")
    public Result findAll() {
        List<UserData> list = userDataService.list();
        return Result.success(list);
    }

    @GetMapping("/find_{id}")
    public UserData findOne(@PathVariable BigInteger id) {
        return userDataService.getById(id);
    }

    @PostMapping("/upload_image_{id}")
    public String setImg(@PathVariable("id") Long id, @RequestParam MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String type = FileUtil.extName(fileName);

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        File uploadFile = new File(avatar_url + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        // 上传文件到磁盘
        file.transferTo(uploadFile);
        // 数据库若不存在重复文件，则不删除刚才上传的文件
        String url = "http://localhost:8080/image/" + fileUUID;
        QueryWrapper<UserData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", id);
        UserData userData = userDataService.getOne(queryWrapper);
        if (userData.getAvatarUrl() == null || userData.getAvatarUrl().equals("") || userData.getAvatarUrl().equals(" ")) {
            userData.setAvatarUrl(url);
        } else {
            File deleteFile = new File(avatar_url + userData.getAvatarUrl().substring(28));
            if (deleteFile.exists()) {
                deleteFile.delete();
            }
            userData.setAvatarUrl(url);
        }
        userDataService.saveOrUpdate(userData);
        return url;
    }
}
