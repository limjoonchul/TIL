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
	private String searchCondition;
	private String searchKeyword;

}
