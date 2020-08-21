package ankang.springmvc.learn.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 自定义类型转换器，实现日期字符串的转换
 * 泛型：
 * S是前台传过来的类型
 * T是转换后的类型
 *
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-21
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        // 完成字符串向日期的转换
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
