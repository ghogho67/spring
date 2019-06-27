package kr.or.ddit.user.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserVoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override//object 인자는 실제 검사 할 인자(Object 객체라서 형변환을 해줘야된다.) error 는 무슨 에러가 뜨는지 알려준다.
	public void validate(Object target, Errors errors) {
		//데이터 검증(우리가 직접만든다.)
		UserVo userVo = (UserVo) target;
		
		//사용자 아이디 길이 4글자 이상(사용자 길이 체크 하는걸 만들었음)
		//사용 법은 검증할 메서드에 userVo 뒤에다가 BindingResult result 를 넣어주면된다. userForm  에 넣어보겟다.
		if(userVo.getName().length() < 2) {
			errors.rejectValue("name", "length");
		}
		
		if(userVo.getUserId().length() <=3) {
			errors.rejectValue("userId", "length");
		}
	}

}
