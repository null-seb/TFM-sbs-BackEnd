package es.upm.tfm_sbs.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@Import(value = {es.upm.tfm_sbs.service.base.handler.CommonMetaObjectHandler.class})
@ComponentScan({"es.upm.tfm_sbs"})
public class ServiceCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class, args);
    }

}
