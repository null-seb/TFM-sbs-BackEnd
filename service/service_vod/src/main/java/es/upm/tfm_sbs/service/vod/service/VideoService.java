package es.upm.tfm_sbs.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;

public interface VideoService {
    String uploadVideo(InputStream file, String originalFilename);
    void removeVideo(String videoId) throws ClientException;

    String getPlayAuth(String videoSourceId) throws ClientException;
}
