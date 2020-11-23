package com.rubypapper.biz.board;

import java.sql.Date;

import lombok.Data;

// VO(Value Object) Ŭ����
/*@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode*/

@Data
public class BoardVO {
	// ���̺��� �÷� �̸�(������ Ÿ�Ա���)�� ������ ��������� private�� �����Ѵ�.
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private String serachCondition;
	private String serachKeyword;

	// SEQ, regDATE,CNT�� �ڵ�����, ����Ʈ���Ǿ��־
	/*
	 * public BoardVO(String title, String writer, String content) { super();
	 * this.title = title; this.writer = writer; this.content = content; }
	 */
	// private ��� ������ �����ϴ� public Getter/Setter �޼ҵ带 �ۼ��Ѵ�.
	// ���� Ű(Alt + Shift + S) �� �̿��Ѵ�.

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
