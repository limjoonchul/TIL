package com.rubypaper.biz.board;

import java.sql.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

// 1. VO(Value Object) Ŭ����
/*@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode*/

//@Data
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
	
	// MultipartFile ��ü�� ����ڰ� ���ε��� ������ ��� ����(������ �̸�, ���, ����Ʈ�迭 ����� ����)�� ��ϵǴ� ��ü
	// ������ �����̳ʰ� MultipartFile ��ü�� �����ϱ� ���ؼ��� CommonsMultipartResolver�� ������ �־�� �Ѵ�.
	private MultipartFile uploadFile;
	
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public int getSeq() {
		return seq;
	}
	@JsonIgnore
	public String getSearchCondition() {
		return searchCondition;
	}
	@JsonIgnore
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getCnt() {
		return cnt;
	}
	
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@JsonIgnore
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		String uploadFileName = uploadFile.getOriginalFilename();
		System.out.println(uploadFileName + " ������ ���ε� ��û�Ǿ��׿�.....");
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", cnt=" + cnt + ", searchCondition=" + searchCondition + ", searchKeyword=" + searchKeyword
				+ "]";
	}

	// SEQ, regDATE,CNT�� �ڵ�����, ����Ʈ���Ǿ��־
	/*
	 * public BoardVO(String title, String writer, String content) { super();
	 * this.title = title; this.writer = writer; this.content = content; }
	 */
	// private ��� ������ �����ϴ� public Getter/Setter �޼ҵ带 �ۼ��Ѵ�.
	// ���� Ű(Alt + Shift + S) �� �̿��Ѵ�.
	

}
