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
	private String serachCondition;
	private String serachKeyword;

	// SEQ, regDATE,CNT는 자동정렬, 디폴트가되어있어서
	/*
	 * public BoardVO(String title, String writer, String content) { super();
	 * this.title = title; this.writer = writer; this.content = content; }
	 */
	// private 멤버 변수에 접근하는 public Getter/Setter 메소드를 작성한다.
	// 단축 키(Alt + Shift + S) 를 이용한다.

	/*
	 * public int getSeq() { return seq; }
	 * 
	 * public void setSeq(int seq) { this.seq = seq; }
	 * 
	 * public String getTitle() { return title; }
	 * 
	 * public void setTitle(String title) { this.title = title; }
	 * 
	 * public String getWriter() { return writer; }
	 * 
	 * public void setWriter(String writer) { this.writer = writer; }
	 * 
	 * public String getContent() { return content; }
	 * 
	 * public void setContent(String content) { this.content = content; }
	 * 
	 * public Date getRegDate() { return regDate; }
	 * 
	 * public void setRegDate(Date regDate) { this.regDate = regDate; }
	 * 
	 * public int getCnt() { return cnt; }
	 * 
	 * public void setCnt(int cnt) { this.cnt = cnt; }
	 */

}
