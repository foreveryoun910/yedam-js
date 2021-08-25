package co.yedam.member.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String id;
	private String name;
	private String phone;
	private Date birth;
	private String address;
}
