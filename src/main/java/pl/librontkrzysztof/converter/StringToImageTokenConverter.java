package pl.librontkrzysztof.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.librontkrzysztof.dao.ImageTokenDao;
import pl.librontkrzysztof.model.ImageToken;

@Component
public class StringToImageTokenConverter implements Converter<Object, ImageToken> {

    static final Logger logger = LoggerFactory.getLogger(StringToImageTokenConverter.class);

    @Autowired
    ImageTokenDao imageToken;

    public ImageToken convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        ImageToken image = imageToken.findById(id);
        return image;
    }
}
