package com.joaoguilhermmy.serialization.converted;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

@Component
public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

    public YamlJackson2HttpMessageConverter() {
        super(new YAMLMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/yaml"));
    }
}
