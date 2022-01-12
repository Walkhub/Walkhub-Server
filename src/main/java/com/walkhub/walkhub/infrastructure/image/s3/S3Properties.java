package com.walkhub.walkhub.infrastructure.image.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.s3")
public class S3Properties {

    private String bucket;

}
