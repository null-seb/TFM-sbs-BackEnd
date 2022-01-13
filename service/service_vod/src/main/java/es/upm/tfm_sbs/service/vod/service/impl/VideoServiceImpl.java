package es.upm.tfm_sbs.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import es.upm.tfm_sbs.common.base.result.ResultCode;
import es.upm.tfm_sbs.service.base.exception.SbsException;
import es.upm.tfm_sbs.service.vod.service.VideoService;
import es.upm.tfm_sbs.service.vod.util.AliyunVodSDKUtils;
import es.upm.tfm_sbs.service.vod.util.VodProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VodProperties vodProperties;

    @Autowired
    public VideoServiceImpl(VodProperties vodProperties) {
        this.vodProperties = vodProperties;
    }

    @Override
    public String uploadVideo(InputStream inputStream, String originalFilename) {

        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        UploadStreamRequest request = new UploadStreamRequest(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret(),
                title, originalFilename, inputStream);

        /* 模板组ID(可选) */
//        request.setTemplateGroupId(vodProperties.getTemplateGroupId());
        /* 工作流ID(可选) */
//        request.setWorkflowId(vodProperties.getWorkflowId());

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = response.getVideoId();
        //没有正确的返回videoid则说明上传失败
        if(StringUtils.isEmpty(videoId)){
            log.error("Ali cloud upload failed：" + response.getCode() + " - " + response.getMessage());
            throw new SbsException(ResultCode.VIDEO_UPLOAD_ALIYUN_ERROR);
        }

        return videoId;
    }

    @Override
    public void removeVideo(String videoId) throws ClientException {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());

        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }
    @Override
    public String getPlayAuth(String videoSourceId) throws ClientException {

        //初始化client对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());

        //创建请求对象
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest ();
        request.setVideoId(videoSourceId);

        //获取响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        return response.getPlayAuth();
    }
}
