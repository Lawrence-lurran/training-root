package com.num.training.controller;

import com.google.code.kaptcha.Producer;
import com.num.training.common.ResponseResult;
import com.num.training.common.ResultInfo;
import com.num.training.constant.Constants;
import com.num.training.handler.exception.MyRuntimeException;
import com.num.training.util.Base64;
import com.num.training.util.IdUtils;
import com.num.training.util.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author ruoyi
 */
@Api(value = "验证码", tags="{验证码接口}")
@RestController
public class CaptchaController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping("/captchaImage")
//    @Log(title = "获取验证码",operatorType = OperatorType.MOBILE,isAuthentication = false)
    public ResponseResult<Map<String ,String >> getCode(){

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        // String captchaType = RuoYiConfig.getCaptchaType();
        // if ("math".equals(captchaType))
        // {
        // String capText = captchaProducerMath.createText();
        // capStr = capText.substring(0, capText.lastIndexOf("@"));
        // code = capText.substring(capText.lastIndexOf("@") + 1);
        // image = captchaProducerMath.createImage(capStr);
        // }
        // else if ("char".equals(captchaType))
        // {
        capStr = code = captchaProducer.createText();
        image = captchaProducer.createImage(capStr);
        // }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            throw new MyRuntimeException(ResultInfo.CAPTCHA_ERROR);

        }
        Map<String ,String > data =new HashMap<>();
        data.put("uuid", uuid);
        data.put("img", Base64.encode(os.toByteArray()));
        return ResponseResult.success("验证码获取成功",data);
    }
}
