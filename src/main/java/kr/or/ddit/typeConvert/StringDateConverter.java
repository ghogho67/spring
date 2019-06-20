package kr.or.ddit.typeConvert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringDateConverter implements Converter<String, Date> {
	
	private String pattern = "yyyy-MM-dd";
	
	@Override
	public Date convert(String source) {
		//source : 2019-08-08 로 왔을경우 이러걸 어떻게 바꿀것인가
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date dt = null;
		try {
			dt = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}

	public void setPattern(String pattern) { // 외부에서 설정을 할수 있게 setter 를 만들어주었다.
		this.pattern = pattern;
	}

}
