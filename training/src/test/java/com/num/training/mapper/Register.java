package com.num.training.mapper;

import com.num.training.domain.entity.UserDO;
import com.num.training.util.ReadExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Description:
 *
 * @author lurran
 * @data Created on 2022/7/26 8:39 下午
 */
@SpringBootTest
public class Register {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Test
    void name() {
    }

    @Test
    public void encoder() throws FileNotFoundException {


        String fileName = "/Users/lurran/Downloads/信息统计表.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        List<UserDO> list = ReadExcelUtil.createExcel(inputStream,false);
        for (UserDO userDO : list) {
            String username = userDO.getUsername();
            String encodePassword=bCryptPasswordEncoder.encode(username);
            userDO.setPassword(encodePassword);
            System.out.println(userDO);
            int insert = userMapper.insert(userDO);
            System.out.println(insert);
        }







    }

}