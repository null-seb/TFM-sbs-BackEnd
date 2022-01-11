package es.upm.tfm_sbs.service.vod.service;

import java.io.InputStream;

public interface VideoService {
    String uploadVideo(InputStream file, String originalFilename);
}
