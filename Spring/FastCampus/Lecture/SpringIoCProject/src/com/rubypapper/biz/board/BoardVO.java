package com.rubypapper.biz.board;

import java.sql.Date;

import lombok.Data;

// VO(Value Object) 클래스
/*@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode*/

@Data
public class BoardVO {
	// 테이블의 컬럼 이름(데이터 타입까지)과 동일한 멤버변수를 private로 선언한다.
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private String searchCondition;
	private String searchKeyword;

}
