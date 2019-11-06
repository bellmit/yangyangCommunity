package cn.yangyang.community.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendSmsUtil {

    @Value("${aliyun.Sms.AccessKeyId}")
    private String AccessKeyId;

    @Value("${aliyun.Sms.AccessSecret}")
    private String AccessSecret;

    @Value("${aliyun.Sms.SignName}")
    private String SignName;

    @Value("${aliyun.Sms.TemplateCodeList}")
    private List<String> TemplateCodeList;

    /**
     * 发送短信
     * @param PhoneNumbers
     * @param TemplateCode
     * @param TemplateParam
     */
    public void SendSms(String PhoneNumbers,Integer TemplateCode,String TemplateParam){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AccessKeyId, AccessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", TemplateCodeList.get(TemplateCode));
        request.putQueryParameter("TemplateParam", String.format("{'code':'%s'}",TemplateParam));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            SendSMSResult(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //短信发送结果
    public void SendSMSResult(String str){
        System.out.println(str);
    }
}
